package game;

import exceptions.GameNotFoundExcpetion;
import exceptions.WrongItemActionException;
import game.items.Actionable;
import game.states.State;
import game.states.Status;
import game.utils.Messages;
import model.Game;

import java.util.List;

/**
  Created by fran on 24/04/16.
 */

public class Controller {

    private static final String tokenSeparator = " ";

    private Game game;


    public enum GameState {
        Lost, Win, InProgress
    }

    public Controller(Game game) {
        this.game = game;
    }

    public String interpretCommand(String command) {
        String[] tokens = command.split(tokenSeparator);
        String action = tokens[0].toLowerCase();
        try {
            if (tokens.length == 1) {
                return game.executeAction(tokens);
            } else {
                switch (action) {
                    case "item":
                        return game.itemHelp(tokens);
                    case "help":
                        return this.checkHelp(command);
                    default:
                        return game.executeActionOnItem(tokens);
                }
            }
        } catch (WrongItemActionException e) {
            return e.getMessage();
        }
    }

    public String interpretCommand(String cmd, String player) {
        game.setActivePlayer(player);
        return interpretCommand(cmd);
    }

    public boolean verify( ) {
        return game.verifyVictory();
    }



    private static String checkHelp( String token) {
        String helpCommand = "help";
        String[] tokens = token.split(tokenSeparator);
        if (tokens.length > 1) {
            try {
                if (tokens[0].contains(helpCommand)) {
                    //return Server.getDescriptionGame(tokens[1]);
                    return "hola";
                }
            } catch (GameNotFoundExcpetion e) {
                System.out.println(Messages.getMessage("gameNotFound"));
            }
        }
        return null;
    }

    public boolean gameOver() {
        return game.gameOver();
    }

    public GameState getGameState() {
        if (verify()) {
            return GameState.Win;
        } else {
            if (gameOver()) {
                return GameState.Lost;
            }
        }
        return GameState.InProgress;
    }

    public GameState getGameState(String player) {
        game.setActivePlayer(player);
        return getGameState();
    }

    public Player getPlayer() {
        return game.getFreePlayer();
    }

    public boolean hasPlayersPlaying() {
        return game.hasPlayersPlaying();
    }

    public void setActivePlayer(String activePlayer) {
        game.setActivePlayer(activePlayer);
    }

    public List<String> getMessages() {
        return game.getMessages();
    }

    public List<String> checkWinners() {
        return game.checkWinners();
    }

    public List<String> checkLosers() {
        return game.checkLosers();
    }

    public void executeTasks(int seconds) {
        game.executeTasks();
    }

    public void simulatePassingOfTime(int seconds) {
        game.simulatePassingOfTime(seconds);
    }

    public State getItemStatus(String item) {
        return game.findItem(item).getStatus();
    }

    public void moveItem(String itemName, String location) {
        game.moveItem(itemName,location);
    }
}

