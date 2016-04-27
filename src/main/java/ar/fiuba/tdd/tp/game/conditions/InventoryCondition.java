package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.random.Util;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fran on 25/04/16.
 */
public class InventoryCondition implements Condition {

    List<Item> inventory;

    public InventoryCondition(List<Item> items) {
        this.inventory = items;
    }

    @Override
    public boolean isValid(Player player) {
        return Util.itemsInInventory(inventory,player.getInventory());
    }
}
