package ua.epam.controller.commands.admin;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.UserRepo;
import ua.epam.models.entities.user.IUser;
import ua.epam.utils.builder.IBuilder;
import ua.epam.utils.builder.UserBuilder;
import ua.epam.utils.validator.user.UserValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


public class AddUserCommand implements ICommand {
    private final AtomicReference<UserRepo> userRepo;

    public AddUserCommand() {
        userRepo = AppContext.USER_REPO;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            IBuilder<IUser> builder = new UserBuilder();
            final IUser user = builder.buildFromHttpReq(req);

            if (UserValidator.validate(user)) {
                userRepo.get().add(user);
                response = ViewPath.FIND_USER_COMMAND;
            }
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}

