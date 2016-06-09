package server.net;


import server.BuilderLoader;
import server.GamePaths;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 Created by ezequiel on 20/04/16.
 */
public class Server {
    public static final String tokenSeparator = " ";
    private static int initialPort = 6800;
    //FOLDER DONDE ESTAN LOS JARS
    //FIXME .properties , argumento del main ?
    //private static final String GAMES_PATH = "E:\\Escritorio\\games";

    private static HashMap<String, String> games;
    private static List<GameServer> gameServers;

    public static void main(String[] argv) throws Exception {
        System.out.println("This is the Server");
/*
        games = new HashMap<>();
        games.put("HANOITOWER", new HanoiTower());
        games.put("BOXGAME", new BoxGame());
        games.put("CURSEDITEM", new CursedItem());
        games.put("ENTERROOM", new EnterRoom());
        games.put("RIVERCROSSING", new RiverCrossing());
        games.put("STICKGAME", new StickGame());
        games.put("TREASUREGAME", new TreasureGame());
*/
        games = new HashMap<>();
        //games.put("HANOITOWER", GamePaths.getGamePath("gameHanoiTower"));
        games.put("BOXGAME", GamePaths.getGamePath("gameBox"));
        games.put("CURSEDITEM",GamePaths.getGamePath("gameCursedItem"));
        games.put("ENTERROOM", GamePaths.getGamePath("gameEnterRoom"));
        games.put("RIVERCROSSING", GamePaths.getGamePath("gameRiverCrossing"));
        games.put("STICKGAME", GamePaths.getGamePath("gameStick"));
        games.put("TREASUREGAME", GamePaths.getGamePath("gameTreasureBox"));
        games.put("TWOPLAYERS", GamePaths.getGamePath("gameTwoPlayers"));
        games.put("ESCAPETWO", GamePaths.getGamePath("gameEscape2"));

        gameServers = new ArrayList<>();
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
        while (input != null && !input.equals("exit")) {
            if (input.toUpperCase().contains(loadGameCommand.toUpperCase())) {
                gameName = input.replace(loadGameCommand,"").toUpperCase();
                try {
                    GameServer gameServer = new GameServer(initialPort, BuilderLoader.load(games.get(gameName)));
                    Thread thread = new Thread(gameServer);
                    thread.start();
                    gameServers.add(gameServer);
                    System.out.println("Game " + gameName + " created in port " + initialPort);
                    initialPort++;
                    //FIXME Agregue generic Excepcion
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            input = inFromUser.readLine();
        }
        terminate();
    }

    private static void terminate() {
        for (GameServer gameServer : gameServers) {
            gameServer.terminate();
        }
    }


}
