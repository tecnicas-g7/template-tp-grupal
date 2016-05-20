package ar.fiuba.tdd.tp.game.actions;


import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.conditions.Condition;
import ar.fiuba.tdd.tp.game.items.Actionable;

import java.util.ArrayList;
import java.util.List;

/*
  Created by fran on 27/04/16.
 */
public class OpenAction extends Action {

    List<Condition> conditions = new ArrayList<>();

    public OpenAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        if (checkConditions(player)) {
            return item.openContainer(player);
        }
        return "Couldnt open container";
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    private boolean checkConditions(Player player) {
        for (Condition condition : conditions) {
            if (!condition.isValid(player)) {
                return false;
            }
        }
        return true;
    }

}
