package ar.fiuba.tdd.tp.game.actions;


import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Describable;

/**
 Created by ltessore on 28/04/16.
 */
public class MoveAction implements Action {
    @Override
    public String getName() {
        return "move";
    }

    @Override
    public String execute(String[] tokens, Player player, Describable item) {
        try {
            Location room = player.getRoom();
            //String result = move(player, room.getItem(tokens[1]), room.getItem(tokens[2]));
            return move(player, room.getItem(tokens[1]), room.getItem(tokens[2]));
        } catch (MaxInventoryException e) {
            //Do nothing
            return null;
        }
    }

    private String move(Player player, Describable stackFrom, Describable stackAfter) throws MaxInventoryException {

        boolean isValid = isMovementValid(stackFrom,stackAfter);

        if (isValid) {
            Describable item = stackFrom.getLast();
            stackFrom.removeComponent(item);
            stackAfter.addComponent(item);
            isValid = true;
        }
        return (isValid) ? "moved!" : "can't move!";
    }

    private Boolean isMovementValid(Describable stackFrom, Describable stackAfter) {
        //chequea si el ultimo del stackAfter es mas grande del que tiene el player
        if (stackFrom.getSize() > 0) {
            if (stackAfter.getSize() == 0) {
                return true;
            } else {
                Describable from = stackFrom.getLast();
                Describable after = stackAfter.getLast();
                if ((Integer.parseInt(after.getName()) > Integer.parseInt(from.getName()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
