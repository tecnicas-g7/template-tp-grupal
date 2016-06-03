
package game.actions;



import java.util.ArrayList;
import java.util.List;


/*
Created by fran on 25/04/16.
*/

import game.Player;
import game.conditions.Condition;
import game.items.Actionable;

public abstract class Action {

    protected String name;

    List<Condition> conditions = new ArrayList<>();

    public Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    abstract void setString(String name);

    public abstract String execute(String[] tokens, Player player, Actionable item);

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    protected boolean checkConditions(Player player) {
        for (Condition condition : conditions) {
            if (!condition.isValid(player)) {
                return false;
            }
        }
        return true;
    }

}
