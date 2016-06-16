package server.net;

import game.Controller;

import exceptions.GameNotFoundExcpetion;
import model.GameBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;


/**
  Created by fran on 22/04/16.
 */
public class GameServer implements Runnable{

    private ServerSocket serverSocket;
    private ServerClient serverClient;
    private ClientSender clientSender;
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
            this.clientSender = new ClientSender(controller);
            this.serverClient = new ServerClient(serverSocket,controller,clientSender);
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
            controller.executeTasks(0);
            List<String> messages = controller.getMessages();
            for (String message : messages) {
                clientSender.broadcast(null,message);
            }
            try {
                sendWinnersLosers();
            } catch (Exception e) {
                //
            }

        }
    }

    private void sendWinnersLosers() throws IOException,InterruptedException {
        List<String> winners = controller.checkWinners();
        for (String winner : winners) {
            clientSender.sendWinMessage(winner);
        }
        List<String> losers = controller.checkLosers();
        for (String loser : losers) {
            clientSender.sendLoseMessage(loser);
        }
        Thread.sleep(25);
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
