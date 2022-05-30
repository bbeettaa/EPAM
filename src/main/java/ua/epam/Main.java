package ua.epam;

import java.sql.*;
import java.util.ResourceBundle;

public class Main {
    private static Connection connect = null;
    private static Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static final String DATABASE_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASS;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("database");
        DATABASE_URL = rb.getString("db.url");
        DATABASE_USER = rb.getString("db.user");
        DATABASE_PASS = rb.getString("db.password");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS).createStatement();
    }
}
