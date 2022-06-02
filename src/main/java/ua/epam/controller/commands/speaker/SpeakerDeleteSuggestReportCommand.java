package ua.epam.controller.commands.speaker;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.EventRepo;
import ua.epam.models.entities.SuggestReportPool;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SpeakerDeleteSuggestReportCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            final String reportName = req.getParameter("reportName");
            SuggestReportPool.getEntity().deleteByName(reportName);
            response = ViewPath.SPEAKER_ALL_SUGGEST_REPORT_COMMAND;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}
