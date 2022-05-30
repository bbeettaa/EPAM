package ua.epam.dao.utils;

import ua.epam.dao.entities.entity.AbstractDao;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    private Connection connection;

    public void initTransaction(AbstractDao... daos) {
        try {
            if (connection == null)
// connection = // code -> create connection or take connection from pool
                connection = ConnectionCreator.createConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (AbstractDao daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    public void endTransaction() {
// connection check code of null
        try {
// connection check code for commit
            connection.setAutoCommit(true);
        } catch (SQLException e) {
// log
        }
// connection return code to the pool or closing
        connection = null;
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
// log
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
// log
        }
    }
}
