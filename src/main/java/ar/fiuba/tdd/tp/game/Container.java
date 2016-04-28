package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.FullCapacityReachedException;
import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;

import java.util.HashMap;

/*
Created by javier on 4/25/16.
*/

public class Container extends Describable {

    private HashMap<String,ContainerComponent> components;
    private int maxSize;
    private boolean hasPoison;

    public Container(String name, int maxSize) {
        super(name);
        this.maxSize = maxSize;
        this.components = new HashMap<>();
    }

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

    @Override
    public void removeComponent(ContainerComponent component) {
        components.remove(component.getName());
    }

    @Override
    public ContainerComponent getChild(String name) {
        return components.get(name);
    }

    @Override
    public String executeAction(String[] tokens, Player player) throws WrongItemActionException {
        return null;
    }

    @Override
    public void addComponent(ContainerComponent component)  throws FullCapacityReachedException {
        if (components.size() < this.maxSize) {
            components.put(component.getName(), component);
        } else {
            throw new FullCapacityReachedException();
        }
    }

}
