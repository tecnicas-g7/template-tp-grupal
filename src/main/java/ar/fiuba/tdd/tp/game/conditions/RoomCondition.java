package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;

/**
 Created by fran on 25/04/16.
 */
public class RoomCondition implements Condition {

    private Location room;
    private boolean valid;

    public RoomCondition(Location room, boolean valid) {
        this.room = room;
        this.valid = valid;
    }

    @Override
    public boolean isValid(Player player) {
        /*if (player.getRoom() == room && valid) {
            return true;
        }
        if (player.getRoom() != room && !valid) {
            return true;
        }
        return false;*/
        if (valid) {
            return (player.getRoom() == room);
        } else {
            return (player.getRoom() != room);
        }
    }
}
