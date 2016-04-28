package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;

/**
 * Created by fran on 27/04/16.
 */
public class OpenAction implements Action {

    public String getName() {
        return "open";
    }

    @Override
    public String execute(String[] tokens, Player player, ContainerComponent item) {
        return item.openContainer(player.getRoom());
    }

}
