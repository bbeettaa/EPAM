package ua.advanced.Practical7_8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionCreator {
    private static final String DATABASE_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASS;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("database");
        DATABASE_URL = rb.getString("db.url");
        DATABASE_USER = rb.getString("db.user");
        DATABASE_PASS = rb.getString("db.password");
    }

    private ConnectionCreator() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASS );
    }
}