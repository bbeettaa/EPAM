package ua.epam.controller.commands.moderator.event;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateEventPage  implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return ViewPath.CREATE_EVENT;
    }
}