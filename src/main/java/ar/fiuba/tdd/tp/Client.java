package ar.fiuba.tdd.tp;

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
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(
                new InputStreamReader(System.in,
                        StandardCharsets.UTF_8
                )
        );
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream(),
                        StandardCharsets.UTF_8
                )
        );
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
    }

}
