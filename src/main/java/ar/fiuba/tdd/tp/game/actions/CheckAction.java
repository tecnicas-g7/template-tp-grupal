package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Describable;


/**
 Created by ltessore on 28/04/16.
 */
public class CheckAction implements Action {
    @Override
    public String getName() {
        return "check";
    }

    @Override
    public String execute(String[] tokens, Player player, Describable item) {
        try {
            return "Size of top from " + tokens[1] + " is " + String.valueOf(player.getRoom().getItem(tokens[1]).getLast().getName());
        } catch (Exception e) {
            return tokens[1] + " is empty.";
        }
    }
}
