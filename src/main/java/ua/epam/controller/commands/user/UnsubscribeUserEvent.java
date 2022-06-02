package ua.epam.controller.commands.user;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnsubscribeUserEvent implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            int subId = Integer.parseInt(req.getParameter("userId"));
            int evId = Integer.parseInt(req.getParameter("id"));
            AppContext.SUBSCRIBES_REPO.get().unsubscribe(subId, evId);
            response = ViewPath.USER_SUBSCRIBES_MENU_COMMAND;

        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}