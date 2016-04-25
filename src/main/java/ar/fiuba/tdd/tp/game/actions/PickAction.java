package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by fran on 25/04/16.
 */

/*
 Pick items from room where player is. Assumes player and item are in same room.
 */

public class PickAction implements Action {

    public String getName() {
        return "pick";
    }

    @Override
    public String execute(String[] tokens, Player player, Item item) {
        try {
            String itemName = item.getName();
            try {
                player.getItem(itemName);
                return "Item not found!";
            } catch (ItemNotFoundException e) {
                player.addItem(item);
                player.getRoom().removeItem(itemName);
                return itemName + " added to inventory!";
            }
        } catch (MaxInventoryException e) {
            return e.getMessage();
        }
    }
}
