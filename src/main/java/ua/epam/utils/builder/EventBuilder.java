package ua.epam.utils.builder;

import ua.epam.AppContext;
import ua.epam.dao.entities.entity.UserDao;
import ua.epam.models.entities.event.Event;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.user.IUser;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class EventBuilder implements IBuilder<IEvent> {


    @Override
    public IEvent buildFromHttpReq(HttpServletRequest req) {
        IEvent entity = new Event();
        try {
            entity.setName(req.getParameter("nameToSet"));
            entity.setTimeBegin(Time.valueOf(LocalTime.parse(req.getParameter("timeToSet"))));
            entity.setDateBegin(LocalDate.parse(req.getParameter("dateToSet")));

        } catch (NullPointerException e) {
            AppContext.LOGGER.error(e.getMessage());
            return null;
        }
        return entity;
    }

    @Override
    public IEvent buildFromSqlReq(ResultSet resultSet) {
        IEvent entity = new Event();
        try {
            entity.setId(resultSet.getInt("event_id"));
            entity.setName(resultSet.getString("event_name"));
            entity.setDateBegin(resultSet.getDate("event_date_start").toLocalDate());
            entity.setTimeBegin(resultSet.getTime("event_time_start"));

        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            return null;
        }
        return entity;
    }
}
