package ua.epam.controller.commands.moderator.event;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.event.IEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

public class SortEvent implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String sort = req.getParameter("sort");
        String order = req.getParameter("order");

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
            req.setAttribute("order","desc");
        }
        else
            req.setAttribute("order","asc");

        eventList.sort(comparator);
        req.setAttribute("dao", eventList);
        return ViewPath.MODERATOR_MENU;
    }
}
