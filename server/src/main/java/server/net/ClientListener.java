package server.net;

import game.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by fran on 03/06/16.
 */
public class ClientListener implements Runnable {

    private Socket socket;
    private Player player;
    //private String player;
    private ClientSender clientSender;

    private boolean running;

    public ClientListener(Socket socket, ClientSender clientSender, Player player) {
        this.socket = socket;
        this.player = player;
        this.clientSender = clientSender;
        this.running = true;
    }

    @Override
    public void run() {

        try {

            clientSender.sendIndividualMessage(player.getName(), "Welcome to game on port " + socket.getLocalPort() + " " + player.getName());
            cycle();
        } catch (IOException e) {
            try {
                terminate();
                player.setPlaying(false);
                clientSender.broadcast(player.getName(), player.getName() + " has exited");
                socket.close();
            } catch (IOException e1) {
                //e1.printStackTrace();
            }
        }
    }

    private void cycle() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        while (running) {
            String userInput = in.readLine();
            if (userInput != null) {
                try {
                    clientSender.interpretCommand(player.getName(),userInput);
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            } else {
                running = false;
                throw new IOException();
            }
        }
    }

    public void terminate() {
        running = false;
    }

}
