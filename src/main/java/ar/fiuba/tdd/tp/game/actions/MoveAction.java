package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;

import java.util.Iterator;

/**
 * Created by ltessore on 28/04/16.
 */
public class MoveAction implements Action {
    @Override
    public String getName() {
        return "move";
    }

    @Override
    public String execute(String[] tokens, Player player, ContainerComponent item) {
        try {
            String result = move(player, player.getItem(tokens[1]), player.getItem(tokens[2]));
            return result;
        } catch (MaxInventoryException e) {
            //Do nothing
            return null;
        }
    }


    private String move(Player player, ContainerComponent stackFrom, ContainerComponent stackAfter) throws MaxInventoryException {
        ContainerComponent item = stackAfter.getLast();
        //Player toma pieza
        player.addItem(item);

        //Mover
        boolean isValid = false;
        if (isMovementValid(player, stackAfter)) {
            stackFrom.removeComponent(item);
            stackAfter.addComponent(item);
            isValid = true;
        }
        //Player deja pieza
        player.removeItem(item.getName());
        return (isValid) ? "moved!" : "can't move!";


    }

    private Boolean isMovementValid(Player player, ContainerComponent stackAfter) {
        // chequea condiciones de movimiento de la pieza
        Iterator<ContainerComponent> it = player.getInventoryIterator();
        //chequea si el ultimo del stackAfter es mas grande del que tiene el player
        if (stackAfter.getSize() == 0
                || (Integer.parseInt(stackAfter.getLast().getName()) > Integer.parseInt(it.next().getName()))) {
            return true;
        }
        return false;
    }
}
