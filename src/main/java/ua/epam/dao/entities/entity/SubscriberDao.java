package ua.epam.dao.entities.entity;

import ua.epam.AppContext;
import ua.epam.dao.utils.ConnectionCreator;
import ua.epam.models.entities.event.IEvent;
import ua.epam.models.entities.user.IUser;
import ua.epam.utils.builder.UserBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriberDao{
    public static final String ALL_EVENTS = "SELECT sub_event_id FROM conferences.subscribes_table WHERE sub_user_id=?;";
    public static final String ADD = "INSERT INTO subscribes_table SET sub_user_id = ?, sub_event_id = ?;";
    public static final String DELETE = "DELETE FROM conferences.subscribes_table WHERE sub_user_id = ? AND sub_event_id = ?;";

    public List<Integer> get(int userId) {
        List<Integer> events = new ArrayList<>();

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(ALL_EVENTS)) {
            prepareStatement.setInt(1, userId);
            prepareStatement.execute();

            ResultSet resultSet = prepareStatement.getResultSet();
            while (resultSet.next()) {
                events.add(resultSet.getInt("sub_event_id"));
            }
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return events;
    }

    public boolean subscribe(int userId, int eventId){
        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(ADD)) {
            prepareStatement.setInt(1, userId);
            prepareStatement.setInt(2, eventId);
            prepareStatement.execute();
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean unsubscribe(int userId, int eventId){
        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(DELETE)) {
            prepareStatement.setInt(1, userId);
            prepareStatement.setInt(2, eventId);
            prepareStatement.execute();
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }
}
