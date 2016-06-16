package server.driver;

import game.Controller;
import game.Player;
import game.states.State;
import model.Game;
import model.GameBuilder;
import server.BuilderLoader;

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

    public Player.GameState getGameState() {
        return controller.getGameState();
    }

    public Player.GameState getGameState(String player) {
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
