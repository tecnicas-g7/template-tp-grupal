package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.Player;

/**
 * Created by fran on 25/04/16.
 */

public interface Condition {

    boolean isValid(Player player);

}
