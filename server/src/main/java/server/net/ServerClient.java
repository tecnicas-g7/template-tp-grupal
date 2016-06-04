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

    public ServerClient(ServerSocket serverSocket, Controller controller) {
        this.serverSocket = serverSocket;
        this.controller = controller;
        running = true;
    }

    public void run() {
        this.clientSender = new ClientSender(controller);
        int i = 0;
        while (running) {
            Socket socket = acceptSocket();
            System.out.println("Hello?");
            if (socket != null) {
                Player player = controller.getPlayer();
                ++i;
                //new Thread(new ClientListener(socket, clientSender, controller, player)).start();
                new Thread(new ClientListener(socket, clientSender, controller, "Player" + i)).start();
                clientSender.addSocket(socket,"Player" + i);
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

    public void terminate() {
        running = false;
    }
}
