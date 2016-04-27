package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by Damian on 25/04/2016.
 */

/*
 By grabbing a poison player is poisoned and not the item is saved.
 */

public class PoisonAction implements Action {

    public String getName() {
        return "poison";
    }

    @Override
    public String execute(String[] tokens, Player player, ContainerComponent item) {
        player.changeStatus(Player.Status.poisoned);
        return "I feel bad!";
    }
}
