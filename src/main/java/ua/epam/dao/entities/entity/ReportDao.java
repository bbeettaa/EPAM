package ua.epam.dao.entities.entity;

import ua.epam.AppContext;
import ua.epam.dao.utils.ConnectionCreator;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.report.IReport;
import ua.epam.models.entities.user.IUser;
import ua.epam.utils.builder.ReportBuilder;
import ua.epam.utils.builder.UserBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDao extends AbstractDao<IReport>{

    public static final String ALL_USER = "Select * from conferences.report_table;";
    public static final String BY_ID = "Select * from conferences.report_table WHERE report_id = ?;";
    public static final String BY_ARGS = "SELECT * FROM conferences.report_table WHERE report_name = ?;";
    public static final String DELETE_BY_ID = "DELETE FROM conferences.report_table WHERE report_id = ?;";
    public static final String UPDATE_BY_ID = "UPDATE report_table SET report_name = ?, " +
            "report_speaker_id = ?, report_event_id = ? WHERE report_id = ?;";
    public static final String ADD = "INSERT INTO report_table SET " +
            "report_name= ?, report_speaker_id= ?, report_event_id= ?;";

    @Override
    public IReport get(String... args) {
        IReport report = null;
        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(BY_ARGS)) {
            prepareStatement.setString(1, args[0]);
            prepareStatement.execute();

            ResultSet resultSet = prepareStatement.getResultSet();
            if (resultSet.next()) {
                report = new ReportBuilder().buildFromSqlReq(resultSet);
                report.setSpeaker(new UserDao().get(report.getSpeakerId()));
            }
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return report;
    }

    @Override
    public List<IReport> get() {
        List<IReport> entities = new ArrayList<>();
        try (ResultSet resultSet = ConnectionCreator.createConnection()
                .createStatement().executeQuery(ALL_USER)) {
            while (resultSet.next()) {
                entities.add(new ReportBuilder().buildFromSqlReq(resultSet));
                IReport report = entities.get(entities.size()-1);
                report.setSpeaker(new UserDao().get(report.getSpeakerId()));
            }
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return entities;
    }

    @Override
    public IReport get(int id) {
        IReport entity = null;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.execute();

            ResultSet resultSet = prepareStatement.getResultSet();
            if (resultSet.next()) {
                entity = new ReportBuilder().buildFromSqlReq(resultSet);
                entity.setSpeaker(new UserDao().get(entity.getSpeakerId()));
            }
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return entity;
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
    public boolean delete(IReport entity) {
        return false;
    }

    @Override
    public boolean create(IReport entity) {
        boolean statement = false;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(ADD)) {
            prepareStatement.setString(1, entity.getName());
            prepareStatement.setInt(2, entity.getSpeakerId());
            prepareStatement.setInt(3, entity.getEventId());
            prepareStatement.execute();
            statement = true;
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return statement;
    }

    @Override
    public IReport update(IReport entity) {
        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(UPDATE_BY_ID)) {
            prepareStatement.setString(1, entity.getName());
            prepareStatement.setInt(2, entity.getSpeakerId());
            prepareStatement.setInt(3, entity.getEventId());
            prepareStatement.setInt(4, entity.getId());
            prepareStatement.execute();

        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            return null;
        }
        return entity;
    }


}