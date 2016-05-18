package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;

/**
 Created by fran on 28/04/16.
 */
public class CloseAction extends Action {

    public CloseAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        return item.closeContainer();
    }
}
