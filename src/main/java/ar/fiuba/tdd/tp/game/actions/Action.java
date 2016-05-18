package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;


/*
Created by fran on 25/04/16.
*/

public interface Action {

    String getName();

    default void setString(String name) {

    }

    String execute(String[] tokens, Player player, Actionable item);

}
