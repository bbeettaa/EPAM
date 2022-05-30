package ua.epam.utils.builder;

import ua.epam.AppContext;
import ua.epam.models.entities.report.IReport;
import ua.epam.models.entities.report.Report;
import ua.epam.models.entities.user.IUser;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class ReportBuilder implements IBuilder<IReport> {


    @Override
    public IReport buildFromHttpReq(HttpServletRequest req) {
        IReport entity = new Report();
        try {
            entity.setName(req.getParameter("nameToSet"));
            IUser speaker = AppContext.USER_REPO.get().get(req.getParameter("speakerLogin"));
            entity.setSpeakerId((nonNull(speaker)) ? speaker.getId() : -1);
            String id = req.getParameter("eventId");
            if(!Objects.equals(id, ""))
            entity.setEventId(Integer.parseInt(id));
        } catch (NullPointerException e) {
            AppContext.LOGGER.error(e.getMessage());
            return null;
        }
        return entity;
    }

    @Override
    public IReport buildFromSqlReq(ResultSet resultSet) {
        IReport user = new Report();
        try {
            user.setId(resultSet.getInt("report_id"));
            user.setName(resultSet.getString("report_name"));
            user.setSpeakerId(resultSet.getInt("report_speaker_id"));
            user.setEventId(resultSet.getInt("report_event_id"));
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            return null;
        }
        return user;
    }
}
