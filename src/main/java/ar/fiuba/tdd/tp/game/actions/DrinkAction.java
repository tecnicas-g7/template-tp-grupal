package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by Javier on 26/04/2016.
 */

public class DrinkAction implements Action {

    public String getName() {
        return "antidote";
    }

    @Override
    public String execute(String[] tokens, Player player, Item item) {
        if (player.getStatus() == Player.Status.poisoned) {
            player.changeStatus(Player.Status.alive);
            return "I feel much better now!";
        }
        return "I've wasted an antidote.";
    }
}
