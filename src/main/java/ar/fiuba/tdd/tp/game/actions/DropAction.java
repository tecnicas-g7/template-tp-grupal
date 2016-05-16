package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Describable;
import ar.fiuba.tdd.tp.game.Player;

/*
   Assumes that the player has an item
*/
public class DropAction implements Action {

    private String name;

    public DropAction() {
        name = "drop";
    }

    public DropAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String execute(String[] tokens, Player player, Describable item) {
        try {
            player.getItem(item.getName());
        } catch (ItemNotFoundException e) {
            return "You don't have that item";
        }
        player.removeItem(item.getName());
        player.getRoom().addDescribable(item);
        return  "You left the item " + item.getName() + " on the floor";
    }
}