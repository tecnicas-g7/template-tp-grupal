package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.FullCapacityReachedException;
import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.actions.Action;

import java.util.Stack;

/**
 * Created by ltessore on 27/04/16.
 */
public class StackContainerComponent extends Describable {

    protected Integer maxSize;
    protected Stack<ContainerComponent> components;

    public StackContainerComponent(String name, int maxSize) {
        super(name);
        this.maxSize = maxSize;
        this.components = new Stack<>();
    }

    public void addComponent(ContainerComponent component)  throws FullCapacityReachedException {
        if (components.size() < this.maxSize) {
            components.add(component);
        } else {
            throw new FullCapacityReachedException();
        }
    }

    public ContainerComponent getLast() {
        return components.lastElement();
    }

    public boolean isFull() {
        if (components.size() == this.maxSize) {
            return true;
        }
        return false;
    }

    //Integer.parseInt(components.lastElement().getName()) > Integer.parseInt(component.getName())

    public Integer getSize() {
        return this.components.size();
    }

    public void removeComponent(ContainerComponent component) {
        components.remove(component);
    }
}
