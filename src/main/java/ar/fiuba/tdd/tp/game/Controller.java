package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;

/**
 * Created by fran on 24/04/16.
 */

public class Controller {

    public static final String tokenSeparator = " ";

    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public String interptetCommand(String command) {
        String[] tokens = command.split(tokenSeparator);
        String action = tokens[0];
        try {
            switch (action) {
                case "look":
                    return game.look();
                case "inventory":
                    return game.showInventory();
                case "enter":
                    return game.enter(tokens);
                default:
                    return game.executeActionOnItem(tokens);
            }
        } catch (WrongItemActionException e) {
            return e.getMessage();
        }

    }


}
