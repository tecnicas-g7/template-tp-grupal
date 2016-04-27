package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;

/**
 * Created by fran on 25/04/16.
 */
public interface Action {

    public String getName();

    public String execute(String[] tokens, Player player, ContainerComponent item);

}
