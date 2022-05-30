package ua.epam.dao.entities.entity;

import ua.epam.AppContext;
import ua.epam.dao.utils.ConnectionCreator;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;
import ua.epam.utils.builder.EventBuilder;
import ua.epam.utils.builder.ReportBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class EventDao extends AbstractDao<IEvent> {

    public static final String ALL = "SELECT * FROM conferences.event_table;";
    public static final String BY_ID = "SELECT * FROM conferences.event_table WHERE event_id = ?;";
    public static final String BY_ARGS = "SELECT * FROM conferences.event_table WHERE event_name = ?;";
    public static final String DELETE_BY_ID = "DELETE FROM conferences.event_table WHERE event_id = ?;";
    public static final String UPDATE_BY_ID = "UPDATE event_table SET " +
            "event_table.event_name = ?, event_date_start = ?, event_time_start = ? WHERE event_id = ?;";
    public static final String ADD_EVENT = "INSERT INTO event_table SET " +
            "event_table.event_name = ?, event_date_start = ?, event_time_start = ?;";

    public static final String ALL_SUB = "SELECT * FROM conferences.subscribes_table where sub_event_id=?;";
    public static final String REPORTS_BY_EVENT_ID = "SELECT * FROM conferences.report_table where report_event_id=?;";

    @Override
    public IEvent get(String... args) {
        IEvent event=null;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(BY_ARGS)) {
            prepareStatement.setString(1, args[0]);
            prepareStatement.execute();

            if(prepareStatement.getResultSet().next())
            event = new EventBuilder().buildFromSqlReq(prepareStatement.getResultSet());

            if(nonNull(event))
            try (PreparedStatement prepareStatementSub = ConnectionCreator.createConnection().prepareStatement(ALL_SUB)) {
                addSubscriber(event, prepareStatementSub);
            }

        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            event = null;
        }
        return event;
    }

    @Override
    public List<IEvent> get() {
        List<IEvent> entities = new ArrayList<>();
        try (ResultSet resultSet = ConnectionCreator.createConnection().createStatement().executeQuery(ALL)) {
            while (resultSet.next()) {
                entities.add(new EventBuilder().buildFromSqlReq(resultSet));
                IEvent event = entities.get(entities.size()-1);
                try (PreparedStatement prepareStatementSub = ConnectionCreator.createConnection().prepareStatement(ALL_SUB)) {
                    addSubscriber(event, prepareStatementSub);
                }
                try (PreparedStatement prepareStatementSub = ConnectionCreator.createConnection().prepareStatement(REPORTS_BY_EVENT_ID)) {
                    addReports(event, prepareStatementSub);
                }
            }
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return entities;
    }

    @Override
    public IEvent get(int id) {
        IEvent event = null;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.execute();

            while(prepareStatement.getResultSet().next()) {
                event = new EventBuilder().buildFromSqlReq(prepareStatement.getResultSet());
                try (PreparedStatement prepareStatementSub = ConnectionCreator.createConnection().prepareStatement(ALL_SUB)) {
                    addSubscriber(event, prepareStatementSub);
                }
                try (PreparedStatement prepareStatementSub = ConnectionCreator.createConnection().prepareStatement(REPORTS_BY_EVENT_ID)) {
                    addReports(event, prepareStatementSub);
                }
            }
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            event = null;
        }
        return event;
    }

    @Override
    public boolean delete(int id) {
        boolean statement;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(DELETE_BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.execute();

            statement = true;
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            statement = false;
        }
        return statement;
    }

    @Override
    public boolean delete(IEvent entity) {
        return false;
    }

    @Override
    public boolean create(IEvent entity) {
        boolean statement = false;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(ADD_EVENT)) {
            prepareStatement.setString(1, entity.getName());
           // prepareStatement.setInt(2, entity.getSpeaker().getId());
            prepareStatement.setDate(2, java.sql.Date.valueOf(entity.getDateBegin()));
            prepareStatement.setTime(3, entity.getTimeBegin());

            prepareStatement.execute();
            statement = true;
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return statement;
    }

    @Override
    public IEvent update(IEvent entity) {
        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(UPDATE_BY_ID)) {
            prepareStatement.setString(1, entity.getName());
            //prepareStatement.setInt(2, entity.getSpeaker().getId());
            prepareStatement.setDate(2, java.sql.Date.valueOf(entity.getDateBegin()));
            prepareStatement.setTime(3, entity.getTimeBegin());
            prepareStatement.setInt(4, entity.getId());
            prepareStatement.execute();

        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            return null;
        }
        return entity;
    }

    private void addSubscriber(IEvent event, PreparedStatement prepareStatementSub) throws SQLException {
        prepareStatementSub.setInt(1, event.getId());
        prepareStatementSub.executeQuery();
        while (prepareStatementSub.getResultSet().next()){
            event.addSubscriber(new UserDao().get(prepareStatementSub.getResultSet().getInt("sub_user_id")));
        }
    }
    private void addReports(IEvent event, PreparedStatement prepareStatementSub) throws SQLException {
        prepareStatementSub.setInt(1, event.getId());
        prepareStatementSub.executeQuery();
        while (prepareStatementSub.getResultSet().next()){
            IReport report = new ReportBuilder().buildFromSqlReq(prepareStatementSub.getResultSet());
            report.setSpeaker(new UserDao().get(report.getSpeakerId()));
            event.addReports(report);
        }
    }
}
