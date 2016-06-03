package game.actions;



/*
Created by Javier on 26/04/2016.
*/

import game.Player;
import game.items.Actionable;
import game.utils.Messages;

public class PlayerStatusAction extends Action {

    private Player.Status status;
    //private String name;

    public PlayerStatusAction(Player.Status status, String name) {
        super(name);
        this.status = status;
        //this.name = name;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        player.changeStatus(status);
        return Messages.getMessage("youAreNow") + " " + status;
    }
}
