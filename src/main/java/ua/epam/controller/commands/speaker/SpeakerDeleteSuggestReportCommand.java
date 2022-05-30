package ua.epam.controller.commands.speaker;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.EventRepo;
import ua.epam.models.entities.SuggestReportPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpeakerDeleteSuggestReportCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            final int id = Integer.parseInt(req.getParameter("reportId"));
            SuggestReportPool.getEntity().getSuggestion().remove(
                    SuggestReportPool.getEntity().getSuggestion().stream().filter(e->e.getId()==id).findFirst().orElse(null)
            );
            response = ViewPath.SPEAKER_ALL_SUGGEST_REPORT_COMMAND;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}
