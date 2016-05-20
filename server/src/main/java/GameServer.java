import game.Controller;
import model.GameBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
  Created by fran on 22/04/16.
 */
public class GameServer implements Runnable{

    private ServerSocket serverSocket;
    Controller controller;
    private GameBuilder gameBuilder;

    public GameServer(int port, GameBuilder gameBuilder) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.gameBuilder = gameBuilder;
    }

    public void run() {
        while (true) {
            try {
                controller = new Controller(gameBuilder.build());
                Socket socket = acceptSocket();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                sendMessage(out, "Welcome to game on port " + serverSocket.getLocalPort());
                while (true) {
                    try {
                        String userInput = in.readLine();
                        String output = controller.interpretCommand(userInput);
                        sendMessage(out, output);
                    } catch (IOException e) {
                        System.out.println("Read failed");
                        break;
                    }
                    if (controller.verify()) {
                        sendMessage(out,"You win!");
                        socket.close();
                        break;
                    }
                }
            } catch (Exception ioe) {
                System.out.println("Error...");
            }
        }
    }

    private Socket acceptSocket() {

        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed.");
        }
        return socket;
    }

    private void sendMessage(DataOutputStream out, String message) throws IOException {
        out.writeBytes(message + '\n');
    }

}
