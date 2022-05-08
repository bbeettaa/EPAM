package ua.advanced.Practical7_8.DAO;

import ua.advanced.Practical7_8.Entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao <T extends Entity> {
    protected Connection connection=null; // or WrapperConnection or ProxyConnection
    // constructors
    public abstract List<T> findAll();
    public abstract T findEntityById(long id);
    public abstract boolean delete(long id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);
    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
// log
        }
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}