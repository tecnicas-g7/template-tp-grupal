package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Item;

/*
   Assumes that the player has an item
*/
public class DropAction implements Action {

    public String getName() {
        return "drop";
    }

    @Override
    public String execute(String[] tokens, Player player, Item item) {
        try {
            player.getItem(item.getName());
        } catch (ItemNotFoundException e) {
            return "You don't have that item";
        }
        player.removeItem(item.getName());
        player.getRoom().addItem(item);
        return  "You left the item " + item.getName() + " on the floor";
    }
}