package game.conditions;



import game.Player;
import game.items.Actionable;
import game.utils.Util;

import java.util.List;

/*
Created by fran on 25/04/16.
*/

public class RoomInventoryCondition implements Condition {

    private List<Actionable> inventory;

    public RoomInventoryCondition(List<Actionable> items) {
        this.inventory = items;
    }

    @Override
    public boolean isValid(Player player) {
        return Util.itemsInInventory(inventory, player.getRoom().getItems());
    }
}
