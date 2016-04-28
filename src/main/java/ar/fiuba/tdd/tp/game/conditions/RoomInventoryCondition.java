package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.utils.Util;

import java.util.List;

/*
Created by fran on 25/04/16.
*/

public class RoomInventoryCondition implements Condition {

    List<ContainerComponent> inventory;

    public RoomInventoryCondition(List<ContainerComponent> items) {
        this.inventory = items;
    }

    @Override
    public boolean isValid(Player player) {
        return Util.itemsInInventory(inventory, player.getRoom().getItems());
    }
}
