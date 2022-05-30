package ua.epam.controller.commands.moderator.report;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.controller.commands.ICommand;
import ua.epam.dao.EventRepo;
import ua.epam.models.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class ReportDeleteCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String response = ViewPath.ERROR_PAGE;
        try {
            req.setCharacterEncoding("UTF-8");
            final String id = req.getParameter("reportId");
            AppContext.REPORTS_REPO.get().delete(Integer.parseInt(id));

            if(AppContext.USER_REPO.get().get(String.valueOf(req.getSession().getAttribute("login"))).getRole()== Role.MODERATOR)
            response = ViewPath.EVENT_INFO_COMMAND;
            else if(AppContext.USER_REPO.get().get(String.valueOf(req.getSession().getAttribute("login"))).getRole()== Role.SPEAKER)
                response = ViewPath.SPEAKER_EVENT_INFO_COMMAND;
        } catch (IOException e) {
            AppContext.LOGGER.error(e.getMessage());
        }

        return response;
    }
}
