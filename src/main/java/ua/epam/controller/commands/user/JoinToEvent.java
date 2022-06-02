package ua.epam.controller.commands.user;

import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.ActiveEventsPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JoinToEvent implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ActiveEventsPool.getEntity().connect(Integer.parseInt(req.getParameter("id")));
        return ViewPath.USER_EVENT_STREAM;
    }
}
