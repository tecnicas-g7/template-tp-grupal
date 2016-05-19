package ar.fiuba.tdd.tp.game.actions;


import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;

/*
  Created by fran on 27/04/16.
 */
public class OpenAction extends Action {

    public OpenAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        return item.openContainer(player);
    }

}
