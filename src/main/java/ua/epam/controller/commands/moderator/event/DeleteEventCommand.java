package ua.epam.controller.commands.moderator.event;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.controller.commands.moderator.event.stream.StopEvent;
import ua.epam.dao.EventRepo;
import ua.epam.dao.UserRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class DeleteEventCommand implements ICommand {
    private final AtomicReference<EventRepo> repo;

    public DeleteEventCommand() {
        repo = AppContext.EVENT_REPO;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            new StopEvent().execute(req,resp);

            req.setCharacterEncoding("UTF-8");
            final String id = req.getParameter("id");
            repo.get().delete(Integer.parseInt(id));
            response = ViewPath.MODERATOR_MENU_COMMAND;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}
