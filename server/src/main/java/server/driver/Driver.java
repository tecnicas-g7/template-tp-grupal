package server.driver;

import game.Controller;
import exceptions.GameNotFoundExcpetion;
import game.items.Actionable;
import game.states.State;
import game.states.Status;
import server.BuilderLoader;

public class Driver implements GameDriver {

    private Controller controller;

    @Override
    public void initGame(String jarPath){
        try {
            controller = new Controller(BuilderLoader.load(jarPath).build());
        } catch (Exception e){
            throw new GameNotFoundExcpetion();
        }
    }

    public String sendCommand(String cmd) {
        return controller.interpretCommand(cmd);
    }

    public Controller.GameState getGameState() {
        return controller.getGameState();
    }

    public State getItemStatus(String item) {
        return controller.getItemStatus(item);
    }

}
