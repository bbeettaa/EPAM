package ua.epam.dao.entities.entity;

import ua.epam.AppContext;
import ua.epam.dao.utils.ConnectionCreator;
import ua.epam.utils.builder.UserBuilder;
import ua.epam.models.entities.user.IUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<IUser> {

    public static final String ALL_USER = "SELECT * FROM conferences.user_table;";
    public static final String BY_ID = "SELECT * FROM conferences.user_table WHERE user_id = ?;";
    public static final String BY_ARGS = "SELECT * FROM conferences.user_table WHERE user_login = ? AND user_pass= ?;";
    public static final String DELETE_BY_ID = "DELETE FROM conferences.user_table WHERE user_id = ?;";
    public static final String UPDATE_BY_ID = "UPDATE conferences.user_table SET user_login = ?, \n" +
            "user_pass = ?, user_email = ?, user_name = ?, user_surname = ?, user_role = ? WHERE user_id = ?;";
    public static final String ADD = "INSERT INTO user_table SET conferences.user_table.user_login = ?, " +
            "user_table.user_pass = ?, user_table.user_email = ?, user_table.user_name = ?, " +
            "user_table.user_surname = ?, user_table.user_role = ?;";

    @Override
    public IUser get(String... args) {
        IUser user = null;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(BY_ARGS)) {
            prepareStatement.setString(1, args[0]);
            prepareStatement.setString(2, args[1]);
            prepareStatement.execute();

            ResultSet resultSet = prepareStatement.getResultSet();
            if (resultSet.next())
                user = new UserBuilder().buildFromSqlReq(resultSet);
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return user;
    }

    @Override
    public List<IUser> get() {
        List<IUser> users = new ArrayList<>();
        try (ResultSet resultSet = ConnectionCreator.createConnection()
                .createStatement().executeQuery(ALL_USER)) {
            while (resultSet.next())
                users.add(new UserBuilder().buildFromSqlReq(resultSet));

        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return users;
    }

    @Override
    public IUser get(int id) {
        IUser user = null;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(BY_ID)) {
            prepareStatement.setInt(1, id);
            prepareStatement.execute();

            ResultSet resultSet = prepareStatement.getResultSet();
            if (resultSet.next())
                user = new UserBuilder().buildFromSqlReq(resultSet);
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return user;
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
    public boolean delete(IUser entity) {
        return false;
    }

    @Override
    public boolean create(IUser entity) {
        boolean statement = false;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(ADD)) {
            prepareStatement.setString(1, entity.getLogin());
            prepareStatement.setString(2, entity.getPassword());
            prepareStatement.setString(3, entity.getEmail());
            prepareStatement.setString(4, entity.getName());
            prepareStatement.setString(5, entity.getSurname());
            prepareStatement.setString(6, entity.getRole().toString());

            prepareStatement.execute();
            statement = true;
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return statement;
    }

    @Override
    public IUser update(IUser entity) {
        IUser user = null;

        try (PreparedStatement prepareStatement = ConnectionCreator.createConnection().prepareStatement(UPDATE_BY_ID)) {
            prepareStatement.setString(1, entity.getLogin());
            prepareStatement.setString(2, entity.getPassword());
            prepareStatement.setString(3, entity.getEmail());
            prepareStatement.setString(4, entity.getName());
            prepareStatement.setString(5, entity.getSurname());
            prepareStatement.setString(6, entity.getRole().toString());
            prepareStatement.setInt(7, entity.getId());
            prepareStatement.execute();

            user = entity;
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
        return user;
    }
}
