
package game.items;

import exceptions.FullCapacityReachedException;
import exceptions.ItemNotFoundException;
import exceptions.MaxInventoryException;
import game.HasItems;
import game.Player;
import game.states.Status;
import game.utils.Messages;
import java.util.HashMap;

/*
Created by javier on 4/25/16.
*/

public class Container extends Actionable implements ContainerComponent, HasItems {

    private HashMap<String, Actionable> components;
    private int maxSize;
    private String lastAction;


    public Container(String name, int maxSize) {
        super(name);
        this.maxSize = maxSize;
        this.components = new HashMap<>();
        this.status = new Status("closed");
        this.lastAction = "";
    }

    public String look() {
        StringBuilder output = new StringBuilder();
        output.append(name);
        if (status.equalState("open")) {
            components.forEach((key, value) -> {
                    output.append(" ");
                    output.append(value.look().concat(" "));
                }
            );
        }
        return output.toString();
    }


    //When the player opens the container, the components in it can be reached
    public String openContainer() {
        StringBuilder output = new StringBuilder();
        status.modifyStatus("open");
        components.forEach((key, value) -> {
                output.append(key);
                output.append(" ");
            }
        );
        output.append(Messages.getMessage("addedToTheRoom"));
        return output.toString();
    }

    public String closeContainer() {
        status.modifyStatus("closed");
        return this.name + " " + Messages.getMessage("hasBeenClosed");
    }

    public String openOrCloseContainer(String action) {

        if (lastAction.equals(action)) {
            return Messages.getMessage("isAlready") + " " + action;
        }
        this.lastAction = action;
        if (status.equalState("open")) {
            return closeContainer();
        }
        return openContainer();
    }

    public void removeComponent(Actionable component) {
        //Util.removeDescribable(components,component.getName());
            if (components.get(component.getName()) != null) {
                components.remove(component.getName());
            } else {
                for (Actionable container: components.values()) {
                    Actionable item = container.getChild(component.getName());
                    if (item != null) {
                        container.removeComponent(item);
                        return;
                    }
                }
            }
    }

    public Actionable getChild(String name) {
        if (status.equalState("open")) {
            return getDescribable(components, name);
        }
        throw new ItemNotFoundException();
    }
/*
    @Override
    public Type getType() {
        return type;
    }
*/
    public void addComponent(Actionable component) throws FullCapacityReachedException {
        if (components.size() < this.maxSize) {
            components.put(component.getName(), component);
        } else {
            throw new FullCapacityReachedException();
        }
    }

    @Override
    public void addItem(Actionable item) throws MaxInventoryException {
        addComponent(item);
    }

    @Override
    public int getInventorySize() {
        return components.size();
    }

    @Override
    public HashMap<String, Actionable> getInventory() {
        return components;
    }

    @Override
    public Actionable getItem(String name) throws ItemNotFoundException {
        return getChild(name);
    }

    @Override
    public void removeItem(String name) {
        removeComponent(getItem(name));
    }

    private Actionable getDescribable(HashMap<String, Actionable> items, String name)
            throws ItemNotFoundException {
        Actionable item = items.get(name);
        if (item != null) {
            return item;
        }
        for (Actionable container : items.values() ) {
            item = container.getChild(name);
            if (item != null) {
                return item;
            }
        }
        throw new ItemNotFoundException();
    }
}
