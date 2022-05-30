package ua.epam.controller.commands.common;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.user.IUser;
import ua.epam.models.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LoginCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = String.valueOf(req.getSession().getAttribute("login"));
        String password = String.valueOf(req.getSession().getAttribute("password"));

        // error handler
        String errorMessage;
        String forward = ViewPath.LOGIN_MENU;

        if (Objects.equals(login, "null") || Objects.equals(password, "null") || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login or password can't be empty";
            req.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        IUser user = AppContext.USER_REPO.get().get(login,password);
        if (user.getLogin() == null ){
            errorMessage = "Cannot find user with such login or password";
            req.setAttribute("errorMessage", errorMessage);
            return forward;
        } else {
            Role userRole = user.getRole();

            req.removeAttribute("action");
            if (userRole == Role.ADMIN) {
                req.setAttribute("dao", AppContext.USER_REPO.get().get());
                forward = ViewPath.FIND_USER_COMMAND;
            }
            else if(userRole == Role.MODERATOR) {
                req.setAttribute("dao", AppContext.EVENT_REPO.get().get());
                forward = ViewPath.MODERATOR_MENU_COMMAND;
            }
            else if(userRole == Role.SPEAKER) {
                forward = ViewPath.SPEAKER_MENU_COMMAND;
            }
            else if (userRole == Role.USER) {
                forward = ViewPath.USER_MENU;
            }


        }
        return forward;
    }
}
