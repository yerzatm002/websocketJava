package kz.meirambekuly.websocketjava.websocket.logs;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/websocket";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Windows12!@";

    @Getter
    @Setter
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            // connection to the database
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connected to database.");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
}
