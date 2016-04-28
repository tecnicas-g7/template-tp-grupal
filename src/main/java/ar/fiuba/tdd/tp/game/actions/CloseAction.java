package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;

/**
 * Created by fran on 28/04/16.
 */
public class CloseAction implements Action {

    public String getName() {
        return "close";
    }

    @Override
    public String execute(String[] tokens, Player player, ContainerComponent item) {
        return item.closeContainer();
    }
}
