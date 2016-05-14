package ar.fiuba.tdd.tp.net;

import ar.fiuba.tdd.tp.exceptions.GameNotFoundExcpetion;
import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
Created by ezequiel on 20/04/16.
 */
public class Server {
    public static final String tokenSeparator = " ";
    private static int initialPort = 6789;

    private static HashMap<String,GameFactory> games;

    public static void main(String[] argv) throws Exception {
        System.out.println("This is the Server");

        games = new HashMap<>();
        games.put("boxGame", new BoxGame());
        games.put("cursedItem", new CursedItem());
        games.put("enterRoom", new EnterRoom());
        games.put("hanoiTower", new HanoiTower());
        games.put("riverCrossing", new RiverCrossing());
        games.put("stickGame", new StickGame());
        games.put("treasureGame", new TreasureGame());
        loadGame();

        /*
        ServerSocket welcomeSocket = new ServerSocket(socketNumber );
        System.out.println("game loaded in " + socketNumber);

        String clientSentence;
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream(),StandardCharsets.UTF_8)
        );

        while (true) {
            clientSentence = inFromClient.readLine();
            if ( clientSentence != null ) {

                String gameFeedback = checkHelp( clientSentence );

                if ( gameFeedback == null ) {
                    gameFeedback = controller.interpretCommand(clientSentence);
                }

                if (controller.verify()) {
                    showMessage(connectionSocket, "You win!");
                }

                showMessage(connectionSocket, gameFeedback);

            }
        }*/
    }

    private static void showMessage(Socket connectionSocket, String gameFeedback) throws IOException {
        DataOutputStream outToClient = new DataOutputStream(
                connectionSocket.getOutputStream()
        );

        outToClient.writeBytes(gameFeedback + '\n');
        outToClient.writeBytes("EOMessage" + '\n');

    }


    private static void loadGame() throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String input = inFromUser.readLine();
        String loadGameCommand = "load game ";
        String gameName;
        while (input != null) {
            if (input.contains(loadGameCommand)) {
                gameName = input.replace(loadGameCommand,"");
                try {
                    (new Thread(new GameServer(initialPort, games.get(gameName)))).start();
                    System.out.println("Game " + gameName + " created in port " + initialPort);
                    initialPort++;
                } catch (GameNotFoundExcpetion e) {
                    System.out.println("Game not Found");
                }
            }
            input = inFromUser.readLine();
        }
    }

    public static Game getGame(String gameName) {
        GameFactory gameFactory = games.get(gameName);
        if (gameFactory != null) {
            return gameFactory.getGame();
        }
        throw new GameNotFoundExcpetion();
    }

    public static String getDescriptionGame(String gameName) {
        GameFactory gameFactory = games.get(gameName);
        if (gameFactory != null) {
            return gameFactory.getHelp();
        }
        throw new GameNotFoundExcpetion();
    }
}


