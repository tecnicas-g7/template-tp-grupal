package game.actions;

import game.Player;
import game.items.Actionable;
import game.states.State;

public class ItemStatusAction extends Action {

    private State status;
    private Actionable item;

    public ItemStatusAction(State status, Actionable item) {
        super("");
        this.status = status;
        this.item = item;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable actionable) {
        item.setNewStatus(status);
        return item.getName() + " has changed status to " + status.getID();
    }
}
