package server;

import game.Controller;

public interface GameDriver {

    void initGame(String jarPath);
    String sendCommand(String cmd);
    Controller.GameState getGameState();

}
