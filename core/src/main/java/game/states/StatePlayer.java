package game.states;

import game.utils.Messages;

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
        return idState.equals(state.getID());
    }

}
