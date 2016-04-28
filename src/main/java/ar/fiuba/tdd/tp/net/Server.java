package ar.fiuba.tdd.tp.net;

import ar.fiuba.tdd.tp.exceptions.GameNotFoundExcpetion;
import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.EnterRoom;
import ar.fiuba.tdd.tp.game.types.StickGame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by ezequiel on 20/04/16.
 */
public class Server {

    public static void main(String[] argv) throws Exception {
        System.out.println("This is the Server");

        //Game game = loadGame();
        loadGame();
        int socketNumber = 6789;
        ServerSocket welcomeSocket = new ServerSocket(socketNumber );
        System.out.println("game loaded in " + socketNumber);

        String clientSentence;
        String capitalizedSentence;
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream(),StandardCharsets.UTF_8)
        );

        while (true) {
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            DataOutputStream outToClient = new DataOutputStream(
                    connectionSocket.getOutputStream()
            );
            outToClient.writeBytes(capitalizedSentence);
        }
    }

    private static Game loadGame() throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String input = inFromUser.readLine();
        String loadGameCommand = "load game ";
        String game = "";
        while (input != null) {
            if (input.contains(loadGameCommand)) {
                game = input.replace(loadGameCommand,"");
                try {
                    return getGame(game);
                } catch (GameNotFoundExcpetion e) {
                    System.out.println("Game not Found");
                }
            }
            input = inFromUser.readLine();
        }
        return null;
    }

    public static Game getGame(String gameName) {
        switch (gameName) {
            case "stickGame":
                return StickGame.getGame();
            case "enterRoom":
                return EnterRoom.getGame();
            default:
                throw new GameNotFoundExcpetion();
        }
    }
}

