package ua.epam.controller.commands.common;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            //session.invalidate();
            session.removeAttribute("login");
            session.removeAttribute("password");
        }
        AppContext.LOGGER.debug("Logout finished");
        return ViewPath.LOGIN_MENU;
    }
}