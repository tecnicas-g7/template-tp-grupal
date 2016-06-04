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
        String output = controller.interpretCommand(message);
        if (controller.verify()) {
            sendIndividualMessage(name,"W");
            socket.close();
            broadcast(name,message);
        }
        if (controller.gameOver()) {
            sendIndividualMessage(name, "L");
            socket.close();
            broadcast(name,message);
        }
        sendIndividualMessage(name, output);
        broadcast(name, message);

    }

    private void broadcast(String name, String message) {
        clients.forEach((playerName,socket) -> {
            if (!playerName.equals(name)) {
                sendIndividualMessage(playerName, name + " execute: " + message);
            }
        } );
    }

    private void sendMessage(DataOutputStream out, String message) throws IOException {
        out.writeBytes(message + '\n');
    }


}
