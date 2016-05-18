package ar.fiuba.tdd.tp.game.conditions;



import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.utils.Util;

import java.util.List;

/*
Created by fran on 25/04/16.
*/

public class InventoryCondition implements Condition {

    private List<Actionable> inventory;
    private boolean valid;

    public InventoryCondition(List<Actionable> items, boolean valid) {
        this.inventory = items;
        this.valid = valid;
    }

    @Override
    public boolean isValid(Player player) {
        boolean valid = Util.itemsInInventory(inventory, player.getInventory());
        return this.valid && valid || !this.valid && !valid;
    }
}
