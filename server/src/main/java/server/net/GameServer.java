package server.net;

import game.Controller;

import exceptions.GameNotFoundExcpetion;
import model.GameBuilder;
import server.net.ClientListener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
  Created by fran on 22/04/16.
 */
public class GameServer implements Runnable{

    private ServerSocket serverSocket;
    private ServerClient serverClient;
    private boolean running;

    Controller controller;
    private GameBuilder gameBuilder;


    public GameServer(int port, GameBuilder gameBuilder) throws IOException {

        this.serverSocket = new ServerSocket(port);

        if (gameBuilder == null) {
            throw new GameNotFoundExcpetion();
        }
        this.gameBuilder = gameBuilder;
        this.running = true;
    }

    public void run() {
        int i = 0;
        while (running) {
            startGame();
            this.serverClient = new ServerClient(serverSocket,controller);
            new Thread(this.serverClient).start();

            //TODO: Terminar juego si todos se desconectan. Y empezar otra vez (volver a empezar el while de arriba).
            while (true) {

            }
            //TODO: Ciclar para tiempo.

        }
    }

    private void startGame() {
        controller = new Controller(gameBuilder.build());
    }

    public void terminate() {
        running = false;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
