package kz.meirambekuly.websocketjava.websocket.server;

import kz.meirambekuly.websocketjava.model.Log;
import kz.meirambekuly.websocketjava.websocket.logs.LogsService;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class UserThread extends Thread{

    private final Socket socket;
    private final Server server;

    private PrintWriter writer;
    private BufferedReader reader;

    @Getter @Setter
    private LogsService logsService = new LogsService();

    @Getter @Setter
    private Long countOfUserLogs = 0L;
    @Getter @Setter
    private Log log = new Log();

    UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();

            // read text to console from user thread
            reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();

            // writes text to console in user thread
            writer = new PrintWriter(output, true);
            writer.println("Hello, Guest! Welcome to our server! " +
                    "In order to use features of the server please type any of this command:");

            String msg;
            do {
                // server suggests 2 main options for user
                // which are creating log and get logs from db
                writer.println("Server has 2 commands allowed for users:\n" +
                        "1. addLog(to add new lgo to the db)     " +
                        "2. logs(to get all logs from db)     " +
                        "3. exit\n" +
                        "Please, type your command: ");
                msg = reader.readLine();

                if(msg.equals("addLog")){
                    writer.println("|Log creation page|\n" +
                            "Log has 1 parameter which is MANDATORY to enter:\n" +
                            "content:     ");
                    // so from db it's clear that for log we should input content of log
                    String content = reader.readLine();

                    // save log to db and return to local post object
                    this.log = logsService.saveLog(content);

                    // here we add post to server just to give the message about it
                    server.addLog(log);

                    writer.println("Log was successfully created!");

                }else if(msg.equals("logs")){
                    // "refresh page" method implemented from the server side
                    // false mean that it's not timer it's request from user
                   List<String> logs = logsService.getAllLogs();
                   writer.println(logs);
                }else if(msg.equals("exit")){
                    return;
                }

            } while (true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

}
