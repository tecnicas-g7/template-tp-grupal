package ar.fiuba.tdd.tp.game.items;

import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.Describable;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.Action;

import java.util.*;

/**
 * Created by fran on 24/04/16.
 */

public class Item extends Describable {

    private HashMap<String,Action> actions;

    public Size getSize() {
        return size;
    }

    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    protected Size size;

    public Item(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Item must have a name.");
        }
        this.actions = new HashMap<>();
        this.name = name;
        this.size = Size.SMALL;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    public String getName() {
        return name;
    }

    public String executeAction(String[] tokens, Player player) throws WrongItemActionException {
        String actionName = tokens[0];
        if (!this.actions.containsKey(actionName)) {
            throw new WrongItemActionException();
        }

        return actions.get(actionName).execute(tokens, player, this);
    }
}
