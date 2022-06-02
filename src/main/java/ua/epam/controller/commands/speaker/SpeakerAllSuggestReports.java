package ua.epam.controller.commands.speaker;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.SuggestReportPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpeakerAllSuggestReports implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");

            req.setAttribute("eventId",req.getParameter("eventId"));
            req.setAttribute("login",String.valueOf(req.getSession().getAttribute("login")));

            int id = AppContext.USER_REPO.get().get(String.valueOf(req.getSession().getAttribute("login"))).getId();
            req.setAttribute("dao", SuggestReportPool.getEntity().getSuggestion(id));
            req.setAttribute("events", AppContext.EVENT_REPO.get());

            response = ViewPath.SPEAKER_SUGGEST_REPORT_PAGE;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return response;
    }
}