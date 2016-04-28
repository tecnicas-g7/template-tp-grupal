package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;

/*
Created by fran on 27/04/16.
*/

public class ThiefAction implements Action {

    @Override
    public String getName() {
        return "talk";
    }

    @Override
    public String execute(String[] tokens, Player player, ContainerComponent item) {
        player.clearInventory();
        return "Hi! \n The " + item.getName() + " has stolen your items!";
    }
}
