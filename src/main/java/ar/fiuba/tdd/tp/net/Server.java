package ar.fiuba.tdd.tp.net;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by ezequiel on 20/04/16.
 */
public class Server {

    public static void main(String[] argv) throws Exception {
        System.out.println("This is the Server");
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream(),StandardCharsets.UTF_8)
            );
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            DataOutputStream outToClient = new DataOutputStream(
                    connectionSocket.getOutputStream()
            );
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}

