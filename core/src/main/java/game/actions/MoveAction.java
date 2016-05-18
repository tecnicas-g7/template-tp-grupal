package game.actions;


import exceptions.MaxInventoryException;
import game.Location;
import game.Player;
import game.items.Actionable;

/**
 Created by ltessore on 28/04/16.
 */
public class MoveAction extends Action {


    public MoveAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        try {
            Location room = player.getRoom();
            //String result = move(player, room.getItem(tokens[1]), room.getItem(tokens[2]));
            return move(player, room.getItem(tokens[1]), room.getItem(tokens[2]));
        } catch (MaxInventoryException e) {
            //Do nothing
            return null;
        }
    }

    private String move(Player player, Actionable stackFrom, Actionable stackAfter) throws MaxInventoryException {

        boolean isValid = isMovementValid(stackFrom,stackAfter);

        if (isValid) {
            Actionable item = stackFrom.getLast();
            stackFrom.removeComponent(item);
            stackAfter.addComponent(item);
            isValid = true;
        }
        return (isValid) ? "moved!" : "can't move!";
    }

    private Boolean isMovementValid(Actionable stackFrom, Actionable stackAfter) {
        //chequea si el ultimo del stackAfter es mas grande del que tiene el player
        if (stackFrom.getSize() > 0) {
            if (stackAfter.getSize() == 0) {
                return true;
            } else {
                Actionable from = stackFrom.getLast();
                Actionable after = stackAfter.getLast();
                if ((Integer.parseInt(after.getName()) > Integer.parseInt(from.getName()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
