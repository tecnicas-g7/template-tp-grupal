package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fran on 21/05/16.
 */
public class ComplexCondition implements Condition {

    List<Condition> conditions = new ArrayList<>();

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    @Override
    public boolean isValid(Player player) {
        for (Condition condition : conditions) {
            if (!condition.isValid(player)) {
                return false;
            }
        }
        return true;
    }
}
