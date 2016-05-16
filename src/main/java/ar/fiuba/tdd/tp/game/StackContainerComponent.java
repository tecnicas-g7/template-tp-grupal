package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.FullCapacityReachedException;

import java.util.Stack;

/**
 Created by ltessore on 27/04/16.
 */
public class StackContainerComponent extends Describable implements ContainerComponent {

    private Integer maxSize;
    private Stack<Describable> components;

    public StackContainerComponent(String name, int maxSize) {
        super(name);
        this.maxSize = maxSize;
        this.components = new Stack<>();
    }

    public void addComponent(Describable component)  throws FullCapacityReachedException {
        if (components.size() < this.maxSize) {
            components.add(component);
        } else {
            throw new FullCapacityReachedException();
        }
    }

    public Describable getLast() {
        return components.lastElement();
    }

    public boolean isFull() {
        return (components.size() == this.maxSize);
    }

    //Integer.parseInt(components.lastElement().getName()) > Integer.parseInt(component.getName())

    public Integer getSize() {
        return this.components.size();
    }

    public void removeComponent(ContainerComponent component) {
        components.remove(component);
    }
}
