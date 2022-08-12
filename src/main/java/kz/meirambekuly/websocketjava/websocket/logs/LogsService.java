package kz.meirambekuly.websocketjava.websocket.logs;

import kz.meirambekuly.websocketjava.model.Log;
import kz.meirambekuly.websocketjava.services.LogService;
import kz.meirambekuly.websocketjava.services.LogServiceImpl;
import kz.meirambekuly.websocketjava.web.LogController;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LogsService {

    private Long last_id = 0L;
    private Long numberOfPosts = 0L;

    public Log saveLog(String text) {

        // saving log using insert into query in prepared statement
        try {
            Long id = 1L;
            PreparedStatement preparedStatement =
                    JdbcConnection.getConnection().prepareStatement
                            ("INSERT INTO log (text)" +
                                    " VALUES (?) ", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, text);

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                // get last id
                last_id = rs.getLong(1);
            }
            System.out.println("Successfully finished saving log to db.");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        // putting data to Log object and returning it
        Log log = Log.builder()
                        .text(text)
                        .build();

        return log;
    }

    public List<String> getAllLogs(){
        // query to get all logs starting from id of post user already seen
        List<String> logs = new ArrayList<>();
        try {
            Statement statement = JdbcConnection.getConnection().createStatement();
            String query = "SELECT * FROM log";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Log log = new Log();

                log.setId(resultSet.getLong("id"));
                log.setText(resultSet.getString("text"));
                logs.add(log.getText());
            }
            System.out.println("Successfully finished getting logs.");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return logs;
    }
}
