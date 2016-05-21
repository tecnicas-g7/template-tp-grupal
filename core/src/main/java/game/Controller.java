package game;

import exceptions.GameNotFoundExcpetion;
import exceptions.WrongItemActionException;
import game.types.GameFactory;
import game.utils.Messages;
import model.Game;
//import net.Server;

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
        String action = tokens[0];
        try {
            switch (action) {
                case "look":
                    return game.look();
                case "inventory":
                    return game.showInventory();
               /* case "enter":
                case "cross":
                    return game.enter(tokens);*/
                case "item":
                    return game.itemHelp(tokens);
                case "help":
                    return this.checkHelp(command);
                default:
                    return game.executeActionOnItem(tokens);
            }
        } catch (WrongItemActionException e) {
            return e.getMessage();
        }
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

    public GameState getGameState() {
        if (verify()) {
            return GameState.Win;
        }
        return GameState.Lost;
    }

}

