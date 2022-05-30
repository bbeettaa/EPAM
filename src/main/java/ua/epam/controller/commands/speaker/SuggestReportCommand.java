package ua.epam.controller.commands.speaker;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.Role;
import ua.epam.models.entities.SuggestReportPool;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;
import ua.epam.models.entities.user.IUser;
import ua.epam.utils.builder.ReportBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static java.util.Objects.isNull;

public class SuggestReportCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");

            String login = req.getParameter("speakerLogin");
            final IUser user = AppContext.USER_REPO.get().get(login);
            final IEvent event = AppContext.EVENT_REPO.get().get(req.getParameter("eventName"));

            if(isNull(event) ){
                req.setAttribute("message","error");
                req.setAttribute("nameToSet",req.getParameter("nameToSet"));
                req.setAttribute("eventId",req.getParameter("eventId"));
                req.setAttribute("eventName",req.getParameter("eventName"));
                req.setAttribute("login",String.valueOf(req.getSession().getAttribute("login")));

                response = ViewPath.SPEAKER_SUGGEST_REPORT;
            } else {
                final IReport entity = new ReportBuilder().buildFromHttpReq(req);
                entity.setSpeakerId((Objects.nonNull(user) ? user.getId() : -1));
                entity.setSpeaker(user);
                entity.setEventId(event.getId());

                String evId = req.getParameter("eventId");

                if(!Objects.equals(evId, "")) {
                    req.setAttribute("id", evId);
                    SuggestReportPool.getEntity().addSuggestion(entity);
                    response = ViewPath.SPEAKER_EVENT_INFO_COMMAND;
                } else {
                    SuggestReportPool.getEntity().addSuggestion(entity);
                    response = ViewPath.SPEAKER_MENU_COMMAND;
                }
            }
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return response;
    }

    private void setError(HttpServletRequest req, int eventId, IReport entity, String error) {
        req.setAttribute("message", error);
        req.setAttribute("report", entity);
        req.setAttribute("eventId", eventId);
    }
}
