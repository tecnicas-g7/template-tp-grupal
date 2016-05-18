package ar.fiuba.tdd.tp.game.actions;


import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.items.Linker;
import ar.fiuba.tdd.tp.game.utils.Messages;

import java.util.HashMap;
import java.util.Iterator;


public class EnterAction extends Action {

    /*public String getName() {
        return "open";
    }*/

    public EnterAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        Location origin = player.getRoom();
        Iterator<HashMap.Entry<String, Linker>> it = origin.getDoorsIterator();
        while (it.hasNext()) {
            Linker door = it.next().getValue();
            Location destination = door.getDestination();
            if (door.getName().equals(tokens[1]) && origin.validLeaveConditions(player) && destination.validEnterConditions(player)) {
                return player.enter(door);
            }
        }
        return Messages.getMessage("youCantDoThatMessage");
    }

}
