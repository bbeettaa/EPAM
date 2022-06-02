package ua.epam.controller.commands.user;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.SubscribeRepo;
import ua.epam.models.entities.ActiveEventsPool;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.user.IUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class UserMenuPage implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        req.setAttribute("started", ActiveEventsPool.getEntity());
        pagination(req, searchName(req, SortEvent(req)));

        return ViewPath.USER_MENU_PAGE;
    }

    private List<IEvent> searchName(HttpServletRequest req, List<IEvent> eventList) {
        String str = (req.getParameter("searchName") == null) ? "" : req.getParameter("searchName");

        List<IEvent> dao = new ArrayList<>();
        String login = String.valueOf(req.getSession().getAttribute("login"));
        String password = String.valueOf(req.getSession().getAttribute("password"));
        IUser user = AppContext.USER_REPO.get().get(login, password);

        List<IEvent> subscribe = new SubscribeRepo().getAllEvents(user.getId());
        for (IEvent event : eventList)
            if (event.getName().contains(str) && isNull(dao.stream().filter(r -> r.getName().equals(event.getName())).findFirst().orElse(null))
                    && isNull(subscribe.stream().filter(s -> s.getId() == event.getId()).findFirst().orElse(null)))
                dao.add(event);

        req.setAttribute("searchName", str);
        req.setAttribute("userId", user.getId());
        return dao;
    }

    private List<IEvent> SortEvent(HttpServletRequest req) {
        String sort = (req.getParameter("sort") == null) ? "" : req.getParameter("sort");
        String order = (req.getParameter("order") == null) ? "" : req.getParameter("order");

        List<IEvent> eventList = AppContext.EVENT_REPO.get().get();
        Comparator<IEvent> comparator = Comparator.comparing(IEvent::getName);

        switch (sort) {
            case "byName":
                req.setAttribute("sort", "byName");
                break;

            case "byDate":
                comparator = Comparator.comparing(IEvent::getDateBegin).thenComparing(IEvent::getTimeBegin);
                req.setAttribute("sort", "byDate");
                break;

            case "bySubscribers":
                comparator = Comparator.comparing(IEvent::getSubCount).reversed();
                req.setAttribute("sort", "bySubscribers");
                break;

            case "byReports":
                comparator = Comparator.comparing(IEvent::getReportsCount).reversed();
                req.setAttribute("sort", "byReports");
                break;
            default:
                req.setAttribute("sort", "byName");
        }

        if ("desc".equals(order)) {
            comparator = comparator.reversed();
            req.removeAttribute("order");
            req.setAttribute("order", "desc");
        } else
            req.setAttribute("order", "asc");

        eventList.sort(comparator);
        return eventList;
    }

    private void pagination(HttpServletRequest req, List<IEvent> eventList) {
        int pageNum = Integer.parseInt(nonNull(req.getParameter("pageNum"))?req.getParameter("pageNum"):"1");
        int contentCount = Integer.parseInt(nonNull(req.getParameter("contentCount"))?req.getParameter("contentCount"):"10");
        int countPages = eventList.size() / contentCount + 1;

        req.setAttribute("pageNum",pageNum);
        req.setAttribute("contentCount",contentCount);
        req.setAttribute("countPages",countPages);

        pageNum--;
        List<IEvent> dao = new ArrayList<>();
        for (int i = pageNum*contentCount; i < pageNum*contentCount+contentCount; i++)
            if (i >= eventList.size())
                break;
            else
                dao.add(eventList.get(i));
        req.setAttribute("dao", dao);
    }
}