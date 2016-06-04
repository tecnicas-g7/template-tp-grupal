package ar.fiuba.tdd.tp.game.conditions;



import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.items.ContainerComponent;

import java.util.List;

/**
 Created by nico on 26/04/16.
 */

public class RoomWithItemsCondition implements Condition {


    private List<Actionable> items;
    private Location room;

    public RoomWithItemsCondition(List<Actionable> items, Location room) {
        this.items = items;
        this.room = room;
    }

    @Override
    public boolean isValid(Player player) {
        for (Actionable item : items) {
            if (!room.getItems().values().stream()
                    .filter(itemInRoom -> itemInRoom.getName().equals(item.getName())).findAny().isPresent() ) {
                return false;
            }
        }
        return true;
    }
}
