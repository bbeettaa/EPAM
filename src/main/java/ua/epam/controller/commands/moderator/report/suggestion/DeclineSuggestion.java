package ua.epam.controller.commands.moderator.report.suggestion;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.models.entities.SuggestReportPool;
import ua.epam.models.entities.report.IReport;
import ua.epam.models.entities.report.Report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeclineSuggestion implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");

            final int evId = Integer.parseInt(req.getParameter("id"));
            IReport entity;

            entity = SuggestReportPool.getEntity().getSuggestion().stream().filter(e->e.getEventId()==evId).findFirst().orElse(null);
           SuggestReportPool.getEntity().getSuggestion().remove(entity);

            response = ViewPath.MODERATOR_SUGGEST_REPORTS_COMMAND;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return response;
    }

}

