package game.conditions;

import game.HasItems;
import game.Location;
import game.Player;
import game.items.Actionable;

/**
 Created by fran on 25/04/16.
 */
public class RoomItemStatusCondition implements Condition {


    private Actionable item;
    private String status;

    public RoomItemStatusCondition(Actionable item, String status) {
        this.item = item;
        this.status = status;
    }

    @Override
    public boolean isValid(Player player) {
        return ((player.getRoom() == this.item.getContainer()) && (this.item.getStatus().equalState(status)));
    }
}
