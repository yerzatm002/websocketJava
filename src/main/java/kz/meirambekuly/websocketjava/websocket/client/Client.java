package kz.meirambekuly.websocketjava.websocket.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String hostname;
    private int port;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try {
            // when we run program in ClientMain
            // it calls method "execute" where we specify our location or hostname and port number
            // to connect to server
            Socket socket = new Socket(hostname, port);

            // gets each message from server side
            new ReadThread(socket, this).start();
            // inputs data while option is not Exit
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
}
