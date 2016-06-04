package server.net;

import game.Controller;
import game.Player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by fran on 03/06/16.
 */
public class ClientListener implements Runnable {

    private Socket socket;
    private Controller controller;
    //private Player player;
    private String player;
    private ClientSender clientSender;

    private boolean running;

    //public ClientListener(Socket socket, ClientSender clientSender, Controller controller, Player player) {
    public ClientListener(Socket socket, ClientSender clientSender, Controller controller, String player) {
        this.socket = socket;
        this.controller = controller;
        this.player = player;
        this.clientSender = clientSender;
        this.running = true;
    }

    @Override
    public void run() {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            //clientSender.sendIndividualMessage(player.getName(), "Welcome to game on port " + socket.getLocalPort());
            clientSender.sendIndividualMessage(player, "Welcome to game on port " + socket.getLocalPort());
            cycle(in);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                //e1.printStackTrace();
            }
        }
    }

    private void cycle(BufferedReader in) {
        while (running) {
            try {
                String userInput = in.readLine();
                //clientSender.interpretCommand(player.getName(),userInput);
                if (userInput != null) {
                    clientSender.interpretCommand(player,userInput);
                }
            } catch (IOException e) {
                terminate();
                controller.disconnect(player);
                break;
            }
        }
    }

    public void terminate() {
        running = false;
    }

}
