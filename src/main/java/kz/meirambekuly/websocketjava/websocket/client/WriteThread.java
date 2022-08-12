package kz.meirambekuly.websocketjava.websocket.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread{
    private PrintWriter writer;
    private Socket socket;
    private Client client;

    public WriteThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);

        String userName=scanner.nextLine();
        writer.println(userName);

        String text;

        do {
            // we can take input till text not equal to "3"
            text = scanner.nextLine();
            writer.println(text);

        } while (!text.equals("3"));

        try {
            System.out.println("Disconnected from server.");
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
