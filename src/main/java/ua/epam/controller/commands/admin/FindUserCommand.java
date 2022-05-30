package ua.epam.controller.commands.admin;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.UserRepo;
import ua.epam.models.entities.user.IUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class FindUserCommand implements ICommand {
    private final AtomicReference<UserRepo> userRepo;

    public FindUserCommand() {
        userRepo = AppContext.USER_REPO;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            final String login = req.getParameter("findLogin");

            final CopyOnWriteArrayList<IUser> users = new CopyOnWriteArrayList<>();
            for (IUser user : userRepo.get().get())
                if (Objects.isNull(login) || user.getLogin().contains(login))
                    users.add(user);

            req.setAttribute("dao", users);
            req.setAttribute("findLogin", login);

            response = ViewPath.ADMIN_MENU;

        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}

