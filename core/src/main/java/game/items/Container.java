package game.items;

import exceptions.FullCapacityReachedException;
import exceptions.ItemNotFoundException;
import exceptions.MaxInventoryException;
import game.HasItems;
import game.Player;
import game.items.type.Type;
import game.utils.Util;

import java.util.HashMap;

/*
Created by javier on 4/25/16.
*/

public class Container extends Actionable implements ContainerComponent, HasItems {

    private HashMap<String, Actionable> components;
    private int maxSize;
    private boolean open;
    private Type type;

    public Container(String name, int maxSize) {
        super(name);
        this.maxSize = maxSize;
        this.components = new HashMap<>();
        this.open = false;
        type = new Type();
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
    public String openContainer(Player player) {
        StringBuilder output = new StringBuilder();
        this.open = true;
        components.forEach((key, value) -> {
                output.append(key);
                output.append(" ");
            }
        );
        output.append("added to the room.");
        return output.toString();
    }

    public String closeContainer() {
        this.open = false;
        return this.name + " has been closed.";
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

    @Override
    public Type getType() {
        return type;
    }

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
