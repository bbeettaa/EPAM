package ua.epam.controller.commands.moderator.report;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.Role;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;
import ua.epam.models.entities.user.IUser;
import ua.epam.utils.builder.EventBuilder;
import ua.epam.utils.builder.ReportBuilder;
import ua.epam.utils.builder.UserBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class CreateReportCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");

            final int evId = Integer.parseInt(req.getParameter("eventId"));
            final IReport entity = new ReportBuilder().buildFromHttpReq(req);
            String login = req.getParameter("speakerLogin");
            final IUser user = AppContext.USER_REPO.get().get(login);

            if (Objects.isNull(user) && Objects.nonNull(login) && !login.isEmpty()) {
                setError(req, evId, entity,"non-existent");
                response = ViewPath.REPORT_CREATE_PAGE;
            } else if (Objects.nonNull(user) && user.getRole()!= Role.SPEAKER) {
                setError(req, evId, entity,"not speaker");
                response = ViewPath.REPORT_CREATE_PAGE;
            }
            else {
                entity.setSpeakerId((Objects.nonNull(user)?user.getId():-1));

                AppContext.REPORTS_REPO.get().add(entity);
                req.setAttribute("id", evId);
                response = ViewPath.EVENT_INFO_COMMAND;
            }
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return response;
    }

    private void setError(HttpServletRequest req, int eventId, IReport entity,String error) {
        req.setAttribute("message", error);
        req.setAttribute("report", entity);
        req.setAttribute("eventId", eventId);
    }
}