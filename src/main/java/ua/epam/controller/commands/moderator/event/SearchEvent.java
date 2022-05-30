package ua.epam.controller.commands.moderator.event;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.event.Event;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.user.IUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class SearchEvent implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String str = req.getParameter("searchName");

        List<IEvent> dao = new ArrayList<>();

        for(IEvent event: AppContext.EVENT_REPO.get().get())
            if(event.getName().contains(str))
                dao.add(event);

        req.setAttribute("dao", dao);
        req.setAttribute("searchName", str);
        req.setAttribute("sort", "byName");
        req.setAttribute("order", "asc");
        return ViewPath.MODERATOR_MENU;
    }
}