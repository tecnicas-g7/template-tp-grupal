
package game.items;

import exceptions.FullCapacityReachedException;
import exceptions.ItemNotFoundException;
import exceptions.MaxInventoryException;
import game.HasItems;
import game.Player;
import game.utils.Messages;
import game.utils.Util;


import java.util.HashMap;

/*
Created by javier on 4/25/16.
*/

public class Container extends Actionable implements ContainerComponent, HasItems {

    private HashMap<String, Actionable> components;
    private int maxSize;
    private boolean open;
    private String lastAction;


    public Container(String name, int maxSize) {
        super(name);
        this.maxSize = maxSize;
        this.components = new HashMap<>();
        this.open = false;
        this.lastAction = "";
    }

    public String look() {
        StringBuilder output = new StringBuilder();
        output.append(name);
        if (this.open) {
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
        this.open = true;
        components.forEach((key, value) -> {
                output.append(key);
                output.append(" ");
            }
        );
        output.append(Messages.getMessage("addedToTheRoom"));
        return output.toString();
    }

    public String closeContainer() {
        this.open = false;
        return this.name + " " + Messages.getMessage("hasBeenClosed");
    }

    public String openOrCloseContainer(String action) {

        if (lastAction.equals(action)) {
            return Messages.getMessage("isAlready") + " " + action;
        }
        this.lastAction = action;
        if (open) {
            return closeContainer();
        }
        return openContainer();
    }

    public void removeComponent(Actionable component) {
        Util.removeDescribable(components,component.getName());
    }

    public Actionable getChild(String name) {
        if (open) {
            return Util.getDescribable(components, name);
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
}
