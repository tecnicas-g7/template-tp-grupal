package game.actions;

import exceptions.FullCapacityReachedException;
import exceptions.InterruptActionException;
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

    public String execute(String[] tokens, Player player, Actionable actionable) throws InterruptActionException {
        if (checkConditions(player)) {
            item.setNewStatus(status);
            return item.getName() + " has changed status to " + status.getID();
        } else {
            throw new InterruptActionException();
        }
    }
}
