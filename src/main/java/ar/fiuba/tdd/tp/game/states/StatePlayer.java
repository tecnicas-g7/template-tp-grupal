package ar.fiuba.tdd.tp.game.states;

import ar.fiuba.tdd.tp.game.utils.Messages;

public class StatePlayer implements State {
    private String idState;

    public StatePlayer(String definition ) {
        this.idState = definition;
    }

    public String getID( ) {
        return  this.idState;
    }

    public String getDescription( ) {
        return Messages.getMessage(this.idState);
    }

    public boolean equals(State state) {
        if (idState.equals(state.getID())) {
            return true;
        } else {
            return false;
        }
    }

}
