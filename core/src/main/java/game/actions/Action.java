package game.actions;


/*
Created by fran on 25/04/16.
*/

import game.Player;
import game.items.Actionable;

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
