package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.FullCapacityReachedException;
import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.actions.Action;
import ar.fiuba.tdd.tp.game.random.Util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by javier on 4/25/16.
 */
public class Container extends Describable {

    private HashMap<String,ContainerComponent> components;
    private int maxSize;
    private boolean open;

    public Container(String name, int maxSize) {
        super(name);
        this.maxSize = maxSize;
        this.components = new HashMap<>();
        this.open = false;
    }

    //When the player opens the container, the components move to the room
    public String openContainer() {
        this.open = true;
        StringBuilder output = new StringBuilder();
        components.forEach((key,value) -> {
                output.append(key + " ");
            }
        );
        output.append("added to the room.");
        return output.toString();
    }

    public String closeContainer() {
        this.open = false;
        return this.name + " has been closed.";
    }

    public void removeComponent(ContainerComponent component) {
        components.remove(component.getName());
    }

    public ContainerComponent getChild(String name) {
        if (open) {
            return Util.getContainerComponent(components,name);
        }
        throw new ItemNotFoundException();
    }

    public void addComponent(ContainerComponent component)  throws FullCapacityReachedException {
        if (components.size() < this.maxSize) {
            components.put(component.getName(), component);
        } else {
            throw new FullCapacityReachedException();
        }
    }
}
