package ua.advanced.Practical7_8.DAO;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MySqlFactory {
    public static MysqlDataSource createMysqlDataSource() {
        MysqlDataSource dataSource = null;
        Properties props = new Properties();
        try {
            InputStream inputStream = new FileInputStream("resources\\database.properties");
            props.load(inputStream);
            dataSource = new MysqlDataSource();
            dataSource.setURL(props.getProperty("db.url"));
            dataSource.setUser(props.getProperty("user"));
            dataSource.setPassword(props.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}