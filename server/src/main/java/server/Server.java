package server;

import exceptions.GameNotFoundExcpetion;
import game.types.*;
import model.Game;
import model.GameBuilder;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 Created by ezequiel on 20/04/16.
 */
public class Server {
    public static final String tokenSeparator = " ";
    private static int initialPort = 6789;

    private static HashMap<String,GameBuilder> games;

    public static void main(String[] argv) throws Exception {
        System.out.println("This is the Server");

        games = new HashMap<>();
        /*games.put("HANOITOWER", new HanoiTower());
        games.put("BOXGAME", new BoxGame());
        games.put("CURSEDITEM", new CursedItem());
        games.put("ENTERROOM", new EnterRoom());
        games.put("RIVERCROSSING", new RiverCrossing());
        games.put("STICKGAME", new StickGame());
        games.put("TREASUREGAME", new TreasureGame());*/
        loadGame();
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
            if (input.toUpperCase().contains(loadGameCommand.toUpperCase())) {
                gameName = input.replace(loadGameCommand,"").toUpperCase();
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
        GameBuilder gameBuilder = games.get(gameName);
        if (gameBuilder != null) {
            return gameBuilder.build();
        }
        throw new GameNotFoundExcpetion();
    }

    public static String getDescriptionGame(String gameName) {
        GameBuilder gameBuilder = games.get(gameName);
        if (gameBuilder != null) {
            return gameBuilder.getHelp();
        }
        throw new GameNotFoundExcpetion();
    }
}
