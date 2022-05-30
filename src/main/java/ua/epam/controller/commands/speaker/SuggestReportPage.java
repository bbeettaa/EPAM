package ua.epam.controller.commands.speaker;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.event.IEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class SuggestReportPage implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");

            if(nonNull(req.getParameter("eventId"))) {
                int id = Integer.parseInt(req.getParameter("eventId"));
                req.setAttribute("eventId", id);
                IEvent event = AppContext.EVENT_REPO.get().get(id);
                req.setAttribute("eventName", event.getName());
            }
            req.setAttribute("login", String.valueOf(req.getSession().getAttribute("login")));

            response = ViewPath.SPEAKER_SUGGEST_REPORT;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return response;
    }
}