package server.driver;

import game.Controller;
import game.states.State;

public interface GameDriver {

    void initGame(String jarPath);
    String sendCommand(String cmd);
    String sendCommand(String cmd, String player);

    Controller.GameState getGameState();

    Controller.GameState getGameState(String player);

    State getItemStatus(String bibliotecario);

    void simulatePassingOfTime(int seconds);

    void moveItem(String itemName,String location);


}
