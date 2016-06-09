package server.net;

import game.Controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by fran on 04/06/16.
 */
public class ClientSender implements Runnable {

    HashMap<String, Socket> clients;
    Controller controller;

    public ClientSender(Controller controller) {
        this.clients = new HashMap<>();
        this.controller = controller;
    }

    @Override
    public void run() {

    }

    public void addSocket(Socket socket, String name) {
        clients.put(name,socket);
    }

    public void sendIndividualMessage(String name, String message) {
        Socket socket = clients.get(name);
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            sendMessage(out,message);
        } catch (IOException e) {
            clients.remove(name);
        }
    }

    public void interpretCommand(String name, String message) throws IOException {
        Socket socket = clients.get(name);
        controller.setActivePlayer(name);
        String output = controller.interpretCommand(message);
        if (controller.verify()) {
            sendIndividualMessage(name, "W");
            socket.close();
            broadcastAction(name,message);
            return;
        }
        if (controller.gameOver()) {
            sendIndividualMessage(name, "L");
            socket.close();
            broadcastAction(name,message);
            return;
        }
        sendIndividualMessage(name, output);
        broadcastAction(name, message);

    }

    private void broadcastAction(String name, String message) {
        for (String playerName : clients.keySet()) {
            if (!playerName.equals(name)) {
                sendIndividualMessage(playerName, name + " execute: " + message);
            }
        }
        /*clients.forEach((playerName, socket) -> {
            System.out.println("?!?!?!?!");
            if (!playerName.equals(name)) {
                sendIndividualMessage(playerName, name + " execute: " + message);
            }
        });*/
    }

    public void broadcast(String name, String message) {
        for (String playerName : clients.keySet()) {
            if (!playerName.equals(name)) {
                sendIndividualMessage(playerName, message);
            }
        }
        /*clients.forEach((playerName, socket) -> {
          if (!playerName.equals(name)) {
                sendIndividualMessage(playerName, message);
            }
        });*/
    }

    private void sendMessage(DataOutputStream out, String message) throws IOException {
        out.writeBytes(message + '\n');
    }


    public void terminate() {
        for (Socket socket : clients.values()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
