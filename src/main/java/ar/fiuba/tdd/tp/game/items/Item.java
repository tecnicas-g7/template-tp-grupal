package ar.fiuba.tdd.tp.game.items;

import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.Action;

import java.util.*;

/**
 * Created by fran on 24/04/16.
 */
public class Item {

    //private List<String> actions;
    private String name;
    private HashMap<String,Action> actions;


    public Item(String name) {
        this.actions = new HashMap<>();
        this.name = name;
    }

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    public String getName() {
        return name;
    }

    public String executeAction(String[] tokens, String actionName, Player player) throws WrongItemActionException {
        if (!this.actions.containsKey(actionName)) {
            throw new WrongItemActionException();
        }

        return actions.get(actionName).execute(tokens, player, this);
    }

}
