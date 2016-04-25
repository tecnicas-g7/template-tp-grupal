package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by fran on 25/04/16.
 */
public interface Action {

    public String getName();

    public String execute(String[] tokens, Player player, Item item);

}
