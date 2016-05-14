package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.List;

/**
 Created by nico on 26/04/16.
 */

public class RoomWithItemsCondition implements Condition {


    private List<Item> items;
    private Room room;

    public RoomWithItemsCondition(List<Item> items, Room room) {
        this.items = items;
        this.room = room;
    }

    @Override
    public boolean isValid(Player player) {
        for (ContainerComponent item : items) {
            if (!room.getItems().values().stream()
                    .filter(itemInRoom -> itemInRoom.getName().equals(item.getName())).findAny().isPresent() ) {
                return false;
            }
        }
        return true;
    }
}
