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

    public String getName() {
        return name;
    }


    public String executeAction(String[] tokens, Player player) throws WrongItemActionException {
        return "";
    }

    public void addComponent(ContainerComponent component)  throws FullCapacityReachedException {
        if (components.size() >= this.maxSize) {
            components.add(component);
        } else {
            throw new FullCapacityReachedException();
        }
    }

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    public ContainerComponent getLast() {
        return components.lastElement();
    }

    public Integer getSize() {
        return this.components.size();
    }

}
