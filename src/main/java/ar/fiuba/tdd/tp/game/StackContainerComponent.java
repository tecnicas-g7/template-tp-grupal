package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.FullCapacityReachedException;
import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.actions.Action;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by ltessore on 27/04/16.
 */
public class StackContainerComponent implements ContainerComponent {

    protected String name;
    protected Integer maxSize;
    protected Stack<ContainerComponent> components;

    public StackContainerComponent(String name, int maxSize) {
        this.maxSize = maxSize;
        this.components = new Stack<>();
    }

    public String getName(){
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

    public ContainerComponent getLast() {
        return components.lastElement();
    }

    //Integer.parseInt(components.lastElement().getName()) > Integer.parseInt(component.getName())
}
