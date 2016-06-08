package game.items;


/*
Created by javier on 4/27/16.
*/

import exceptions.WrongItemActionException;
import game.Player;


public interface ContainerComponent {

    String getName();



    String executeAction(String[] tokens, Player player) throws WrongItemActionException;
/*
    default String openContainer(Player player) {
        return "";
    }
*/
    default Integer getSize() {
        return null;
    }
/*
    default String closeContainer() {
        return null;
    }
*/
    default String openOrCloseContainer(Player player) {
        return "";
    }

    default String look() {
        return getName();
    }

    String showActions();
}
