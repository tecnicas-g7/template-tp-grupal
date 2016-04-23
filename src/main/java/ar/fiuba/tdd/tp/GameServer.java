package ar.fiuba.tdd.tp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by fran on 22/04/16.
 */
public class GameServer implements Runnable{

    private ServerSocket serverSocket;

    public GameServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void run() {
        Socket socket = acceptSocket();
        BufferedReader in = null;
        DataOutputStream out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e ) {
            System.out.println("Buffer failed");
        }
        sendMessage(out,"Welcome to game on port " + serverSocket.getLocalPort());
        while (true) {
            try {
                String userInput = in.readLine();
                System.out.println("Received: " + userInput);
                sendMessage(out,userInput);
            } catch (IOException e ) {
                System.out.println("Read failed");
                break;
            }
        }
    }

    public Socket acceptSocket() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed.");
        }
        return socket;
    }

    public void sendMessage(DataOutputStream out, String message) {
        try {
            out.writeBytes(message + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
