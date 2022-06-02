package ua.epam.controller.commands.common;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.Role;
import ua.epam.models.entities.report.IReport;
import ua.epam.models.entities.user.IUser;
import ua.epam.utils.builder.IBuilder;
import ua.epam.utils.builder.ReportBuilder;
import ua.epam.utils.builder.UserBuilder;
import ua.epam.utils.validator.user.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class RegistrationCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;

        IBuilder<IUser> builder = new UserBuilder();
        final IUser user = builder.buildFromHttpReq(req);

        IUser userByEmail = AppContext.USER_REPO.get().getByEmail(user.getEmail());

        String email = req.getParameter("emailToSet");
        //login already exists
        if (nonNull(AppContext.USER_REPO.get().get(user.getLogin()))) {
            setError(req, user, "login exists");
            response = ViewPath.REGISTRATION_PAGE;
        }
        //email already exists'
        else if (!Objects.equals(email, "") && nonNull(userByEmail)) {
            setError(req, user, "email exists");
            response = ViewPath.REGISTRATION_PAGE;
        } else if (UserValidator.validate(user)) {
            AppContext.USER_REPO.get().add(user);

            req.setAttribute("login", user.getLogin());
            req.setAttribute("password", user.getPassword());
            response = ViewPath.LOGIN_COMMAND;

        }

        return response;
    }

    private void setError(HttpServletRequest req, IUser user, String error) {
        req.setAttribute("message", error);
        req.setAttribute("user", user);
        req.setAttribute("loginToSet", user.getLogin());
        req.setAttribute("passwordToSet", user.getPassword());
        req.setAttribute("emailToSet", user.getEmail());
        req.setAttribute("nameToSet", user.getName());
        req.setAttribute("surnameToSet", user.getSurname());
        req.setAttribute("role", user.getRole());
    }
}