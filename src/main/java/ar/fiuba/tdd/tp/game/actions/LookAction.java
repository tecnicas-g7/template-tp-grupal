package ar.fiuba.tdd.tp.game.actions;


import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.items.Linker;
import ar.fiuba.tdd.tp.game.utils.Messages;

import java.util.HashMap;
import java.util.Iterator;


public class LookAction extends Action {

    public LookAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        Location location = player.getRoom();
        return location.look();
    }

}
