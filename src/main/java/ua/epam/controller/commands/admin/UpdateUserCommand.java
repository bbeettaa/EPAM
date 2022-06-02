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
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class UpdateUserCommand implements ICommand {
    private final AtomicReference<UserRepo> userRepo;

    public UpdateUserCommand() {
        userRepo = AppContext.USER_REPO;
    }

/*    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");

            final String id = req.getParameter("id");
            final IUser user = new UserBuilder().buildFromHttpReq(req);
            user.setId(Integer.parseInt(id));

            if (UserValidator.validate(user)) {
                userRepo.get().update(user);
                response = ViewPath.ALL_USERS_COMMAND;
            }
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return response;
    }*/
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;

        final String id = req.getParameter("id");

        IUser oldUser = AppContext.USER_REPO.get().get(Integer.parseInt(id));
        final IUser user = new UserBuilder().buildFromHttpReq(req);
        user.setId(Integer.parseInt(id));

        String email = req.getParameter("emailToSet");

        IUser userByEmail = AppContext.USER_REPO.get().getByEmail(user.getEmail());
        IUser userByLogin = AppContext.USER_REPO.get().get(user.getLogin());
        try{
            //login already exists
            if (nonNull(userByLogin) && userByLogin.getId() != user.getId()) {
                setError(req, user, "login exists");
                response = ViewPath.UPDATE_USER;
            }
            //email already exists'
            else if (!Objects.equals(email, "") && nonNull(userByEmail) && userByEmail.getId() != user.getId()) {
                setError(req, user, "email exists");
                response = ViewPath.UPDATE_USER;
            } else if (UserValidator.validate(user)) {
                AppContext.USER_REPO.get().update(user);

                req.setAttribute("login", user.getLogin());
                req.setAttribute("password", user.getPassword());
                response = ViewPath.LOGIN_COMMAND;
            }
        }catch (Exception e){
            AppContext.LOGGER.error(e.getMessage());
        }finally {
           // AppContext.USER_REPO.get().add(oldUser);

        }
        return response;
    }
    private void setError(HttpServletRequest req, IUser user, String error) {
        req.setAttribute("message", error);
        req.setAttribute("user", user);
        req.setAttribute("loginToSet",user.getLogin());
        req.setAttribute("passwordToSet",user.getPassword());
        req.setAttribute("emailToSet",user.getEmail());
        req.setAttribute("nameToSet",user.getName());
        req.setAttribute("surnameToSet",user.getSurname());
        req.setAttribute("role",user.getRole());
    }
}

