package ar.fiuba.tdd.tp.net;

import ar.fiuba.tdd.tp.exceptions.GameNotFoundExcpetion;
import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.types.GameFactory;

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
    private GameFactory gameFactory;

    public GameServer(int port, GameFactory gameFactory) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.gameFactory = gameFactory;
    }

    public void run() {
        while (true) {
            try {
                controller = new Controller(gameFactory.getGame());
                Socket socket = acceptSocket();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                sendMessage(out, "Welcome to game on port " + serverSocket.getLocalPort());
                cycle(in,out,socket);
            } catch (Exception ioe) {
                System.out.println("Error...");
            }
        }
    }

    private void cycle(BufferedReader in, DataOutputStream out, Socket socket) throws Exception {
        while (true) {
            String output;
            try {
                String userInput = in.readLine();
                output = controller.interpretCommand(userInput);
                if (controller.verify()) {
                    sendMessage(out,"W");
                    socket.close();
                    break;
                }
                if (controller.gameOver()) {
                    sendMessage(out,"L");
                    socket.close();
                    break;
                }
                sendMessage(out,output);
            } catch (IOException e) {
                System.out.println("Read failed");
                break;
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
