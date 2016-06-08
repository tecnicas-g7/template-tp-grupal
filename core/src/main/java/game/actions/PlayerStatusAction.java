package game.actions;







/*
Created by Javier on 26/04/2016.
*/

import game.Player;
import game.items.Actionable;
import game.states.State;
import game.utils.Messages;

public class PlayerStatusAction extends Action {

    private State status;
    //private String name;

    public PlayerStatusAction(State status, String name) {
        super(name);
        this.status = status;
        //this.name = name;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        player.changeStatus(status);
        return Messages.getMessage("youAreNow") + " " + status.getDescription();
    }
}
