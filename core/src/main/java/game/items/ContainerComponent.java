package game.items;


/*
Created by javier on 4/27/16.
*/

import exceptions.WrongItemActionException;
import game.Player;
import game.items.type.Type;

public interface ContainerComponent {

   /* default void addComponent(ContainerComponent component) {

    }

    default void removeComponent(ContainerComponent component) {

    }
*/
    String getName();

    Type getType();

    String executeAction(String[] tokens, Player player) throws WrongItemActionException;

    default String openContainer(Player player) {
        return "";
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
