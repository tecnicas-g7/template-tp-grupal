package game.items;

import exceptions.WrongItemActionException;
import game.HasItems;
import game.Location;
import game.Player;
import game.actions.Action;
import game.states.State;
import game.states.Status;
import game.utils.Messages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Actionable {

    protected String name;
    protected HashMap<String,Action> actions;
    protected State status;
    protected HasItems container;

    public Actionable(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(Messages.getMessage("ItemMustHaveAName"));
        }
        this.actions = new HashMap<>();
        this.name = name;
        status = new Status("inanimate");
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

    public String openOrCloseContainer(String action) {
        return name + " " + Messages.getMessage("IsNotContainer");
    }
/*
    private String closeContainer() {
        return Messages.getMessage("YouCannotClose") + " " + name;
    }

    private String openContainer(Player player) {
        return Messages.getMessage("YouCanNotOpen") + " " + name;
    }
*/
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

    public boolean isValidMovement(Actionable item) {
        return true;
    }

    public void setNewStatus(State state) {
        this.status = state;
    }

    public State getStatus() {
        return status;
    }

    public void setContainer(HasItems container) {
        this.container = container;
    }

    public HasItems getContainer() {
        return this.container;
    }
}
