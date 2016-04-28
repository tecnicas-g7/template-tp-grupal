package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.FullCapacityReachedException;
import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.game.items.type.Type;
import ar.fiuba.tdd.tp.game.utils.Util;

import java.util.HashMap;

/*
Created by javier on 4/25/16.
*/

public class Container extends Describable {

    private HashMap<String, ContainerComponent> components;
    private int maxSize;
    private boolean hasPoison;
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
                    output.append(" " + value.look() + " ");
                }
            );
        }
        return output.toString();
    public void yesPoison() {
        this.hasPoison = true;
    }

    public void noPoison() {
        this.hasPoison = false;
    }

    //When the player opens the container, the components move to the room
    public void openContainer(Room room, Player player) {
        if (this.hasPoison) {
            player.changeStatus(Player.Status.poisoned);
            this.noPoison();
        }
        components.forEach((key,value) -> room.addContainerComponent(value));
        components.clear();
    }

    //When the player opens the container, the components in it can be reached
    public String openContainer() {
        this.open = true;
        StringBuilder output = new StringBuilder();
        components.forEach((key, value) -> {
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
            return Util.getContainerComponent(components, name);
        }
        throw new ItemNotFoundException();
    }

    @Override
    public Type getType() {
        return type;
    }

    public void addComponent(ContainerComponent component) throws FullCapacityReachedException {
        if (components.size() < this.maxSize) {
            components.put(component.getName(), component);
        } else {
            throw new FullCapacityReachedException();
        }
    }
}
