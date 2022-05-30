package ua.epam.controller.commands.moderator;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModeratorMenu implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("dao", AppContext.EVENT_REPO.get().get());
        req.setAttribute("sort","byName");
        req.setAttribute("order","asc");
        return ViewPath.MODERATOR_MENU;
    }
}