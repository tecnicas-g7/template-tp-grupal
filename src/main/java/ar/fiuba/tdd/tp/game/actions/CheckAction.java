package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;


/**
 Created by ltessore on 28/04/16.
 */
public class CheckAction extends Action {

    public CheckAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        try {
            return "Size of top from " + tokens[1] + " is " + String.valueOf(player.getRoom().getItem(tokens[1]).getLast().getName());
        } catch (Exception e) {
            return tokens[1] + " is empty.";
        }
    }
}
