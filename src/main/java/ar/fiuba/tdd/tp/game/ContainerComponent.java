package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;

/**
 * Created by javier on 4/27/16.
 */
public interface ContainerComponent {
    default void addComponent(ContainerComponent component) {

    }

    default void removeComponent(ContainerComponent component) {

    }

    default ContainerComponent getChild(String name) {
        return null;
    }

    String getName();

    String executeAction(String[] tokens, Player player) throws WrongItemActionException;

    default String openContainer(Room room) {
        return null;
    }
}