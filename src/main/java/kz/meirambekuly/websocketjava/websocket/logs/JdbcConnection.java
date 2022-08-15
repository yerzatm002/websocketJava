package kz.meirambekuly.websocketjava.websocket.logs;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static final String URL = "jdbc:postgresql://SG-websocket-3849-pgsql-master.servers.mongodirector.com:5432/websocket";
    private static final String USERNAME = "sgpostgres";
    private static final String PASSWORD = "k991TZNa4g2dI$c5";

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
