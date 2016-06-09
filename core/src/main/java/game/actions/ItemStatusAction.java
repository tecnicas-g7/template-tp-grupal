package game.actions;

import game.Player;
import game.items.Actionable;
import game.states.State;

public class ItemStatusAction extends Action {

    private State status;

    public ItemStatusAction(State status) {
        super("");
        this.status = status;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        item.setNewStatus(status);
        return " ";
    }
}
