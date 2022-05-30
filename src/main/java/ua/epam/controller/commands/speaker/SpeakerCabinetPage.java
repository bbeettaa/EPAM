package ua.epam.controller.commands.speaker;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.entities.services.DaoUserService;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.user.IUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

public class SpeakerCabinetPage implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        searchName(req, SortEvent(req));
        return ViewPath.SPEAKER_MENU_PAGE;
    }

    private String searchName(HttpServletRequest req, List<IEvent> eventList) {
        String str = (req.getParameter("searchName")==null)?"":req.getParameter("searchName");

        List<IEvent> dao = new ArrayList<>();
        String login = String.valueOf(req.getSession().getAttribute("login"));
        String password = String.valueOf(req.getSession().getAttribute("password"));
        IUser user = AppContext.USER_REPO.get().get(login,password);

        for(IEvent event: eventList)
            if(event.getName().contains(str) && isNull(dao.stream().filter(r->r.getName().equals(event.getName())).findFirst().orElse(null)))
                dao.add(event);

        req.setAttribute("dao", dao);
        req.setAttribute("searchName", str);
        req.setAttribute("sort", "byName");
        return ViewPath.SPEAKER_MENU_PAGE;
    }

    private List<IEvent> SortEvent(HttpServletRequest req){
        String sort = (req.getParameter("sort")==null)?"":req.getParameter("sort");
        String order = (req.getParameter("order")==null)?"":req.getParameter("order");

        List<IEvent> eventList = AppContext.EVENT_REPO.get().get();
        Comparator<IEvent> comparator =Comparator.comparing(IEvent::getName);

        switch (sort){
            case "byName":
                req.setAttribute("sort","byName");
                break;

            case "byDate":
                comparator=Comparator.comparing(IEvent::getDateBegin).thenComparing(IEvent::getTimeBegin);
                req.setAttribute("sort","byDate");
                break;

            case "bySubscribers":
                comparator=Comparator.comparing(IEvent::getSubCount).reversed();
                req.setAttribute("sort","bySubscribers");
                break;

            case "byReports":
                comparator=Comparator.comparing(IEvent::getReportsCount).reversed();
                req.setAttribute("sort","byReports");
                break;
        }

        if ("desc".equals(order)) {
            comparator=comparator.reversed();
            req.removeAttribute("order");
            req.setAttribute("order","desc");
        }
        else
            req.setAttribute("order","asc");

        eventList.sort(comparator);
        //req.setAttribute("dao", eventList);
        return eventList;
    }
}