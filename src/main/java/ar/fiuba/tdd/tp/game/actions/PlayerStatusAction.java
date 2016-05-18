package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;

/*
Created by Javier on 26/04/2016.
*/

public class PlayerStatusAction implements Action {

    private Player.Status status;
    private String name;

    public PlayerStatusAction(Player.Status status, String name) {
        this.status = status;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        player.changeStatus(status);
        return "You are now " + status;
    }
}
