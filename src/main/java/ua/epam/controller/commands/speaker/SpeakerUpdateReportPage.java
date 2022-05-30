package ua.epam.controller.commands.speaker;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.entities.services.DaoReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpeakerUpdateReportPage implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");

            req.setAttribute("name",AppContext.REPORTS_REPO.get().get(Integer.parseInt(req.getParameter("reportId"))).getName());
            req.setAttribute("reportId",req.getParameter("reportId"));
            req.setAttribute("eventId",req.getParameter("eventId"));
            req.setAttribute("login",String.valueOf(req.getSession().getAttribute("login")));

            response = ViewPath.SPEAKER_REPORT_UPDATE;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return response;
    }
}