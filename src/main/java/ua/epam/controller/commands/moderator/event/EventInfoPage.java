package ua.epam.controller.commands.moderator.event;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.event.IEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class EventInfoPage implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String idStr = String.valueOf(req.getAttribute("id"));
        if(Objects.equals(idStr, "null"))
            idStr = req.getParameter("id");

        IEvent event = AppContext.EVENT_REPO.get().get(Integer.parseInt(idStr));
        req.setAttribute("event", event);
        return ViewPath.EVENT_INFO;
    }
}