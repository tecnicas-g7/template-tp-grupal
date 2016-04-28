package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.Container;
import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.Iterator;

/**
 Created by ltessore on 28/04/16.
 */
public class MoveAction implements Action {
    @Override
    public String getName() {
        return "move";
    }

    @Override
    public String execute(String[] tokens, Player player, ContainerComponent item) {
        try {
            Room room = player.getRoom();
            //String result = move(player, room.getItem(tokens[1]), room.getItem(tokens[2]));
            return move(player, room.getItem(tokens[1]), room.getItem(tokens[2]));
        } catch (MaxInventoryException e) {
            //Do nothing
            return null;
        }
    }

    private String move(Player player, ContainerComponent stackFrom, ContainerComponent stackAfter) throws MaxInventoryException {

        boolean isValid = isMovementValid(stackFrom,stackAfter);

        if (isValid) {
            ContainerComponent item = stackFrom.getLast();
            stackFrom.removeComponent(item);
            stackAfter.addComponent(item);
            isValid = true;
        }
        return (isValid) ? "moved!" : "can't move!";
    }

    private Boolean isMovementValid(ContainerComponent stackFrom, ContainerComponent stackAfter) {
        //chequea si el ultimo del stackAfter es mas grande del que tiene el player
        if (stackFrom.getSize() > 0) {
            if (stackAfter.getSize() == 0) {
                return true;
            } else {
                ContainerComponent from = stackFrom.getLast();
                ContainerComponent after = stackAfter.getLast();
                if ((Integer.parseInt(after.getName()) > Integer.parseInt(from.getName()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
