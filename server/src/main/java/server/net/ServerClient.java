package server.net;

import exceptions.GameNotFoundExcpetion;
import game.Controller;
import game.Player;
import model.GameBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by fran on 04/06/16.
 */
class ServerClient implements Runnable{

    private ServerSocket serverSocket;
    private boolean running;

    private ClientSender clientSender;

    Controller controller;

    public ServerClient(ServerSocket serverSocket, Controller controller, ClientSender clientSender) {
        this.serverSocket = serverSocket;
        this.controller = controller;
        running = true;
        this.clientSender = clientSender;
    }

    public void run() {
        while (running) {
            Socket socket = acceptSocket();
            if (socket != null) {
                checkForFreePlayers(socket);
            }
        }
    }

    private void checkForFreePlayers(Socket socket) {
        Player player = controller.getPlayer();
        if (player != null) {
            new Thread(new ClientListener(socket, clientSender, player)).start();
            //new Thread(new ClientListener(socket, clientSender, controller, "Player" + i)).start();
            clientSender.addSocket(socket, player.getName());
            //clientSender.addSocket(socket,"Player" + i);
        } else {
            sendMessage(socket,"No free players, closing conection...");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Socket acceptSocket() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            terminate();
        }
        return socket;
    }

    public void terminate() {
        running = false;
        clientSender.terminate();
    }

    public void sendMessage(Socket socket, String message) {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeBytes(message + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
