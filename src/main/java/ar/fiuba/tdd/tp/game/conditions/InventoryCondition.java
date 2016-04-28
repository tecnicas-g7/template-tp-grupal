package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.random.Util;

import java.util.List;

/*
Created by fran on 25/04/16.
*/

public class InventoryCondition implements Condition {

    List<ContainerComponent> inventory;
    private boolean valid;

    public InventoryCondition(List<ContainerComponent> items, boolean valid) {
        this.inventory = items;
        this.valid = valid;
    }

    @Override
    public boolean isValid(Player player) {
        boolean valid = Util.itemsInInventory(inventory, player.getInventory());
        return this.valid && valid || !this.valid && !valid;
    }
}
