package game.conditions;


import game.Location;
import game.Player;
import game.items.Actionable;

/**
 Created by nico on 27/04/16.
 */
public class ItemTypeCondition implements Condition {

    @Override
    public boolean isValid(Player player) {
        return !hasConflictsInRoom(player.getRoom());
    }

    //Chequeamos si hay conflictos con los elementos dentro de la room

    private Boolean hasConflictsInRoom(Location room) {
        for (Actionable item: room.getItems().values() ) {
            if (room.getItems().values().parallelStream().anyMatch(
                    item1 -> item1.getType().isEnemy(item.getType()))) {
                return true;
            }
        }
        return false;
    }

}