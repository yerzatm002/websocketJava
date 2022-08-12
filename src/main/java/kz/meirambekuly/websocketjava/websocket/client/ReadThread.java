package kz.meirambekuly.websocketjava.websocket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{
    private BufferedReader reader;
    InputStream inputStream;

    private Socket socket;
    private Client client;

    public ReadThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            inputStream = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + response);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
    }
}
