package ua.epam.controller.commands.moderator.report;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;
import ua.epam.utils.builder.EventBuilder;
import ua.epam.utils.builder.ReportBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReportUpdatePage implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            final int repId = Integer.parseInt(req.getParameter("reportId"));
            final int evId = Integer.parseInt(req.getParameter("eventId"));
            final IReport entity = AppContext.REPORTS_REPO.get().get(repId);
            //entity.setId(Integer.parseInt(id));
            //AppContext.REPORTS_REPO.get().update(entity);

            req.setAttribute("report",entity);
            req.setAttribute("reportId",repId);
            req.setAttribute("eventId",evId);
            response = ViewPath.REPORT_UPDATE_PAGE;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return response;
    }
}