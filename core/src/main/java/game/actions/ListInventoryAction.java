package game.actions;


import game.Player;
import game.items.Actionable;

public class ListInventoryAction extends Action {

    public ListInventoryAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        return player.showInventory();
    }

}
