package ar.fiuba.tdd.tp.game.items;

import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.Action;
import ar.fiuba.tdd.tp.game.items.type.Type;

import java.util.HashMap;

public abstract class Actionable {

    protected String name;
    private Type type;

    protected HashMap<String,Action> actions;

    public Actionable(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Item must have a name.");
        }
        this.actions = new HashMap<>();
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
        actions.forEach((key,value) -> output.append(key.concat(" ")));
        output.append("with ");
        output.append(name);
        return output.toString();
    }

    //@Override
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String closeContainer() {
        return "You cannot close " + name;
    }

    public String openContainer(Player player) {
        return "You cannot open " + name;
    }

    public Actionable getChild(String name) {
        return null;
    }

    public String look() {
        return getName();
    }

    public void removeComponent(Actionable item) {

    }

    public void addComponent(Actionable item) {

    }

    public Integer getSize() {
        return null;
    }

    public Actionable getLast() {
        return null;
    }
}
