package ua.epam.dao.entities.entity;

import ua.epam.AppContext;
import ua.epam.models.entities.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<T extends Entity> {
    protected Connection connection = null; // or WrapperConnection or ProxyConnection


    public abstract T get(String... args);

    public abstract List<T> get();

    public abstract T get(int id);

    public abstract boolean delete(int id);

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity);

    public abstract T update(T entity);

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            AppContext.LOGGER.error(e.getMessage());
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}