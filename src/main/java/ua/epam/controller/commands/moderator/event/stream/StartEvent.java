package ua.epam.controller.commands.moderator.event.stream;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.ActiveEventsPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartEvent implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ActiveEventsPool.getEntity().add(AppContext.EVENT_REPO.get().get(Integer.parseInt(req.getParameter("id"))));
        return ViewPath.EVENT_INFO_COMMAND;
    }
}