package ua.epam.controller.commands.moderator.event;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.event.IEvent;
import ua.epam.utils.builder.EventBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateEvent implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            final String id = req.getParameter("id");
            final IEvent entity = new EventBuilder().buildFromHttpReq(req);
            entity.setId(Integer.parseInt(id));

            AppContext.EVENT_REPO.get().update(entity);
            req.setAttribute("id",id);
            response = ViewPath.EVENT_INFO_COMMAND;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return response;
    }
}