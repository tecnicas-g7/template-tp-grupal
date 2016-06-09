package game.states;

import game.utils.Messages;

public class Status implements State {
    private String idState;

    public Status(String definition ) {
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

    public boolean equalState(String state) {
        return idState.equalsIgnoreCase(state);
    }

    public void modifyStatus(String newStatus) {
        idState = newStatus;
    }

}
