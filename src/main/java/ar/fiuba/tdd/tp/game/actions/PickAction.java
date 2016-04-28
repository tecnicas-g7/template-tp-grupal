package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by fran on 25/04/16.
 */

/*
 Pick items from room where player is. Assumes player and item are in same room.
 */

public class PickAction implements Action {

    private String name;

    public String getName() {
        return name;
    }

    public PickAction() {
        name = "pick";
    }

    public PickAction(String name) {
        this.name = name;
    }

    @Override
    public String execute(String[] tokens, Player player, ContainerComponent item) {
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
