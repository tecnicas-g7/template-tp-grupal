package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Describable;
import ar.fiuba.tdd.tp.game.Player;

/*
Created by fran on 25/04/16.
*/

public interface Action {

    String getName();

    String execute(String[] tokens, Player player, Describable item);

}
