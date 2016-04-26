package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fran on 25/04/16.
 */

/*
public class RoomInventoryCondition implements Condition {

    List<Item> inventory;

    public RoomInventoryCondition(List<Item> items) {
        this.inventory = items;
    }

    @Override
    public boolean isValid(Player player) {
        HashMap<String,Item> roomInventory = player.getRoom().getItems();
        for (Item item : this.inventory) {
            if (!roomInventory.containsValue(item)) {
                return false;
            }
        }
        return true;
    }
}
*/