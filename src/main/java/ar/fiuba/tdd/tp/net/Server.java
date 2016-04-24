package ar.fiuba.tdd.tp.net;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by ezequiel on 20/04/16.
 */
public class Server {

    private static int currentPort = 6789;

    public static void main(String[] argv) throws Exception {
        System.out.println("This is the Server");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String input = bufferedReader.readLine();
        while (input != null && !input.equals("exit")) {
            if (input.equals("load game")) {
                try {
                    newGame(currentPort);
                    System.out.println("Game loaded and listening on port " + currentPort);
                    currentPort++;
                } catch (IOException e) {
                    System.out.println("Could not listen on port: " + currentPort);
                }
            }
            input = bufferedReader.readLine();
        }

    }

    public static void newGame(int port) throws IOException {
        new Thread(new GameServer(port)).start();
    }

}

