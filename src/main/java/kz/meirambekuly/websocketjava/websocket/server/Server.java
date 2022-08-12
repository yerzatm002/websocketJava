package kz.meirambekuly.websocketjava.websocket.server;

import kz.meirambekuly.websocketjava.model.Log;
import kz.meirambekuly.websocketjava.websocket.logs.LogsService;
import lombok.Getter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Server {
    @Getter
    //map with username and user's threads parameters
    private final Map<String, UserThread> userThreadMap=new HashMap<>();
    private LogsService logService;

    @Getter
    private int port;

    public Server(){}

    public Server(int port) {
        this.port=port;
    }

    public UserThread getUserThread(String username) {
        return userThreadMap.get(username);
    }

    public void addUserThread(String username, UserThread userThread) {
        userThreadMap.put(username, userThread);
        System.out.println("User "+ username +" added to server");
    }

    public void addLog(Log log){
        System.out.println("New log was added to server");
    }

    public void exec(){
        ServerSocket serverSocket= null;
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Waiting for clients...");

        while (true){
            // every time waiting new clients to proceed command after login
            Socket socket= null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            UserThread userThread=new UserThread(socket, this);
            userThread.start();
        }
    }

    public void refreshPage(String username, boolean isItTimer) {
        UserThread thread = getUserThread(username);


        if(thread.isAlive()){
            if(isItTimer){
                thread.sendMessage("|Each 10 minutes server checks new logs and sends them if they exist|");
            }else
                thread.sendMessage("Page refreshed successfully! New Logs available, check it now:");

            for(String log: logService.getAllLogs()){
                getUserThread(username).sendMessage(log);
            }
        }else if(!isItTimer){
            // if user chose refresh page and there are no new logs then this text appears
            thread.sendMessage("=======================================\n" +
                    "There is no new logs on server." +
                    "\n=======================================");
        }
    }
}
