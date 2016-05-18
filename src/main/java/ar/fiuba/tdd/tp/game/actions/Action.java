package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;


/*
Created by fran on 25/04/16.
*/

public abstract class Action {

    protected String name;

    public Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    abstract void setString(String name);

    public abstract String execute(String[] tokens, Player player, Actionable item);

}
