package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.items.type.Type;
import com.sun.glass.ui.Size;

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

    Type getType();

    String executeAction(String[] tokens, Player player) throws WrongItemActionException;

    default String openContainer() {
        return null;
    }

    default ContainerComponent getLast() {
        return null;
    }

    default Integer getSize() {
        return null;
    }

    default String closeContainer() {
        return null;
    }

    default String look() {
        return getName();
    }

    String showActions();
}
