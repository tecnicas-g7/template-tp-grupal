package game.items;

import exceptions.WrongItemActionException;
import game.Player;
import game.actions.Action;
import game.items.type.Type;
import game.utils.Messages;

import java.util.HashMap;

public abstract class Actionable {

    protected String name;
    private Type type;

    protected HashMap<String,Action> actions;

    public Actionable(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(Messages.getMessage("ItemMustHaveAName"));
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
        StringBuilder output = new StringBuilder(Messages.getMessage("youCan") + " ");
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
        return Messages.getMessage("YouCannotClose") + " " + name;
    }

    public String openContainer(Player player) {
        return Messages.getMessage("YouCanNotOpen") + " " + name;
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
