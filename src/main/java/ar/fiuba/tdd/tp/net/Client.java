package ar.fiuba.tdd.tp.net;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by ezequiel on 2
 * 0/04/16.
 */
public class Client {

    public static void main(String[] argv) throws Exception {
        System.out.println("This is the Client");
        String input;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        input = inFromUser.readLine();
        while (input != null && !input.equals("exit")) {
            String[] message = input.split(" ");
            if (message[0].equals("connect")) {
                String[] address = message[1].split(":");
                Socket socket = new Socket(address[0], Integer.parseInt(address[1]));

                play(socket);
            }
            input = inFromUser.readLine();
        }
    }

    private static void play(Socket socket) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String inputClient = inFromUser.readLine();

        while (inputClient != null && !inputClient.equals("exit game")) {

            outToServer.writeBytes(inputClient + '\n');
            getServerInput(inFromServer);
            inputClient = inFromUser.readLine();

        }
        socket.close();
    }

    private static void getServerInput(BufferedReader inFromServer) throws IOException {
        String inputServer = inFromServer.readLine();
        while (inputServer != null) {
            if (inputServer.equals("EOMessage")) {
                break;
            }
            System.out.println(inputServer);
            inputServer = inFromServer.readLine();
        }
    }

}