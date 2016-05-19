package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.utils.Messages;

/*
Created by Javier on 26/04/2016.
*/

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
