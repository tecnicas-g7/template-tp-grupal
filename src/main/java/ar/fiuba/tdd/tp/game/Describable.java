package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.actions.Action;

import java.util.HashMap;

public abstract class Describable implements ContainerComponent {

    protected String name;

    protected HashMap<String,Action> actions;

    public Describable(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Item must have a name.");
        }
        this.actions = new HashMap<String,Action>();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    public String executeAction(String[] tokens, Player player) throws WrongItemActionException {
        String actionName = tokens[0];
        if (!this.actions.containsKey(actionName)) {
            throw new WrongItemActionException();
        }
        return actions.get(actionName).execute(tokens, player, this);
    }

    public String showActions() {
        StringBuilder output = new StringBuilder("You can ");
        actions.forEach((key,value) -> output.append(key + " "));
        output.append("with " + name);
        return output.toString();
    }
}
