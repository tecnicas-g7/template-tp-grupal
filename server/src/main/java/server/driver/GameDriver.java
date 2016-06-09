package server.driver;

import game.Controller;
import game.states.State;

public interface GameDriver {

    void initGame(String jarPath);
    String sendCommand(String cmd);

    Controller.GameState getGameState();

    State getItemStatus(String bibliotecario);
}
