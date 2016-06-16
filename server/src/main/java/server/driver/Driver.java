package server.driver;

import game.Controller;
import exceptions.GameNotFoundExcpetion;
import game.items.Actionable;
import game.states.State;
import game.states.Status;
import model.Game;
import model.GameBuilder;
import server.BuilderLoader;

import java.io.IOException;

public class Driver implements GameDriver {

    private Controller controller;

    @Override
    public void initGame(String jarPath){

        GameBuilder gameBuilder = null;
        try {
            gameBuilder = BuilderLoader.load(jarPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Game game = gameBuilder.build();
            controller = new Controller(game);

    }

    public String sendCommand(String cmd) {
        return controller.interpretCommand(cmd);
    }

    public String sendCommand(String cmd, String player) {
        return controller.interpretCommand(cmd,player);
    }

    public Controller.GameState getGameState() {
        return controller.getGameState();
    }

    public Controller.GameState getGameState(String player) {
        return controller.getGameState(player);
    }

    public State getItemStatus(String item) {
        return controller.getItemStatus(item);
    }

    public void simulatePassingOfTime(int seconds) {
        controller.simulatePassingOfTime(seconds);
    }

    public void moveItem(String itemName,String location) {
        controller.moveItem(itemName,location);
    }
}
