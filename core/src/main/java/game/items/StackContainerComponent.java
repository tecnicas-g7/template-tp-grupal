package game.items;

import exceptions.FullCapacityReachedException;

import java.util.Stack;

/**
 Created by ltessore on 27/04/16.
 */
public class StackContainerComponent extends Actionable implements ContainerComponent {

    private Integer maxSize;
    private Stack<Actionable> components;

    public StackContainerComponent(String name, int maxSize) {
        super(name);
        this.maxSize = maxSize;
        this.components = new Stack<>();
    }

    private Boolean isMovementValid(Actionable component) {
        //chequea si el ultimo del stackAfter es mas grande del que tiene el player
        if (this.getSize() == 0) {
            return true;
        } else {
            Actionable after = this.getLast();
            if ((Integer.parseInt(after.getName()) > Integer.parseInt(component.getName()))) {
                return true;
            }
        }
        return false;
    }


    public void addComponent(Actionable component)  throws FullCapacityReachedException {
        if (components.size() < this.maxSize && isMovementValid(component)) {
            components.add(component);
        } else {
            throw new FullCapacityReachedException();
        }
    }

    public Actionable getLast() {
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
