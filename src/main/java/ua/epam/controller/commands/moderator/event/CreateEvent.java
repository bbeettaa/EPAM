package ua.epam.controller.commands.moderator.event;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.EventRepo;
import ua.epam.models.entities.event.Event;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.user.IUser;
import ua.epam.utils.builder.EventBuilder;
import ua.epam.utils.builder.IBuilder;
import ua.epam.utils.builder.UserBuilder;
import ua.epam.utils.validator.user.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class CreateEvent implements ICommand {
    private final AtomicReference<EventRepo> repo;

    public CreateEvent() {
        repo = AppContext.EVENT_REPO;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            final IEvent event = new EventBuilder().buildFromHttpReq(req);

                repo.get().add(event);
                response = ViewPath.MODERATOR_MENU_COMMAND;

        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}
