package server;

import game.Controller;
import exceptions.GameNotFoundExcpetion;

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

}
