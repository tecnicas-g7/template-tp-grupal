package server.net;

import game.Controller;

import exceptions.GameNotFoundExcpetion;
import model.GameBuilder;

import java.io.IOException;
import java.net.ServerSocket;


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
        while (running) {
            startGame();
            this.serverClient = new ServerClient(serverSocket,controller);
            new Thread(this.serverClient).start();

            boolean waiting = true;
            while (running && waiting) {
                if (controller.hasPlayersPlaying()) {
                    waiting = false;
                }
            }
            cycle();
            serverClient.terminate();
        }
    }

    private void cycle() {
        //TODO: Ciclo para pasar tiempo de juego
        boolean gameRunning = true;
        while (running && gameRunning) {
            if (!controller.hasPlayersPlaying()) {
                gameRunning = false;
            }
            //Meter ciclo....
            
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
