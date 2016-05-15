package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Describable;
import ar.fiuba.tdd.tp.game.Player;

/*
Created by Javier on 26/04/2016.
*/

public class DrinkAction implements Action {

    public String getName() {
        return "drink";
    }

    @Override
    public String execute(String[] tokens, Player player, Describable item) {
        if (player.getStatus() == Player.Status.poisoned) {
            player.changeStatus(Player.Status.alive);
            player.removeItem(item.getName());
            return "I feel much better now!";
        }
        return "I've wasted an antidote.";
    }
}
