package game.actions;

import game.Location;
import game.Player;
import game.items.Actionable;
import game.items.Linker;
import game.utils.Messages;
import java.util.Iterator;
import java.util.Map;

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
        Iterator<Map.Entry<String, Linker>> it = origin.getDoorsIterator();
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
