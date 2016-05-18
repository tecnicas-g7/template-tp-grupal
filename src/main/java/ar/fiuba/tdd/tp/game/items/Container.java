package ar.fiuba.tdd.tp.game.items;

import ar.fiuba.tdd.tp.exceptions.FullCapacityReachedException;
import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.type.Type;
import ar.fiuba.tdd.tp.game.utils.Util;

import java.util.HashMap;

/*
Created by javier on 4/25/16.
*/

public class Container extends Actionable implements ContainerComponent {

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
}
