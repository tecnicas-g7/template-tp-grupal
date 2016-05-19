package ar.fiuba.tdd.tp.game.actions;


import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;


public class ListInventoryAction extends Action {

    public ListInventoryAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        return player.showInventory();
    }

}
