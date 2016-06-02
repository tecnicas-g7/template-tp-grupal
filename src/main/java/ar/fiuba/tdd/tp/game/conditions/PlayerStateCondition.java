package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.states.State;

/**
 Created by fran on 28/04/16.
 */
public class PlayerStateCondition implements Condition {

    private State status;

    public PlayerStateCondition(State status) {
        this.status = status;
    }

    @Override
    public boolean isValid(Player player) {
   //     return ( player.getStatus() != status);
        return (! player.getStatus().equals(status));
    }
}
