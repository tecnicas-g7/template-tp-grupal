package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.FullCapacityReachedException;
import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.actions.Action;

import java.util.HashMap;

/**
 * Created by javier on 4/25/16.
 */
public class Container extends Describable {

    private HashMap<String,ContainerComponent> components;
    private int maxSize;

    public Container(String name, int maxSize) {
        super(name);
        this.maxSize = maxSize;
        this.components = new HashMap<>();
    }

    //When the player opens the container, the components move to the room
    public String openContainer(Room room) {
        StringBuilder output = new StringBuilder();
        components.forEach((key,value) -> {
                room.addContainerComponent(value);
                output.append(key + " ");
            }
        );
        components.clear();
        output.append("added to the room.");
        return output.toString();
    }

    public void removeComponent(ContainerComponent component) {
        components.remove(component.getName());
    }

    public ContainerComponent getChild(String name) {
        return components.get(name);
    }

    public void addComponent(ContainerComponent component)  throws FullCapacityReachedException {
        if (components.size() < this.maxSize) {
            components.put(component.getName(), component);
        } else {
            throw new FullCapacityReachedException();
        }
    }
}
