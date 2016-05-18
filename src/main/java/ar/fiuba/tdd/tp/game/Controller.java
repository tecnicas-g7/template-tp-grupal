package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.GameNotFoundExcpetion;
import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.utils.Messages;
import ar.fiuba.tdd.tp.net.Server;

/**
  Created by fran on 24/04/16.
 */

public class Controller {

    private static final String tokenSeparator = " ";

    private Game game;

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
        String[] tokens = token.split(Server.tokenSeparator);
        if (tokens.length > 1) {
            try {
                if (tokens[0].contains(helpCommand)) {
                    return Server.getDescriptionGame(tokens[1]);
                }
            } catch (GameNotFoundExcpetion e) {
                System.out.println(Messages.getMessage("gameNotFound"));
            }
        }
        return null;
    }
}
