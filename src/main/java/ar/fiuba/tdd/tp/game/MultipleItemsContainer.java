package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ContainerNotFoundException;

import java.util.HashMap;

/**
 * Created by javier on 4/25/16.
 */
public class MultipleItemsContainer extends Container {

    private HashMap<String,SingleItemContainer> containers;



    public MultipleItemsContainer(String name) {
        super(name);
        this.containers = new HashMap<>();
    }

    public void addContainer(SingleItemContainer container) {
        containers.put(container.getName(),container);
    }

    public SingleItemContainer getContainer(String name) {
        SingleItemContainer container = this.containers.get(name);
        if (container != null) {
            return container;
        }
        throw new ContainerNotFoundException();
    }
}
