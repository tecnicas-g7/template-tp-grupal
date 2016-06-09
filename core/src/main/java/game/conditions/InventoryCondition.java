package game.conditions;



import game.Player;
import game.items.Actionable;

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
        valid = hasItemsInInventory(player);
        return this.valid && valid || !this.valid && !valid;
    }

    private boolean hasItemsInInventory(Player player) {
        for (Actionable item : inventory) {
            if (!player.getInventory().containsValue(item)) {
                return false;
            }
        }
        return true;
    }
}
