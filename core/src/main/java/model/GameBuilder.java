package model;

import game.Location;
import game.Player;
import game.actions.EnterAction;
import game.actions.ListInventoryAction;
import game.actions.LookAction;
import game.items.Actionable;

public interface GameBuilder {

    Game build();
    String getName();
    String getHelp();

    default void makeLocationsAdjacent(Location room1, Location room2, Actionable key) {
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2,key,enterAction);
        room2.addDoor(room1,key,enterAction);
    }

    default Player createPlayer(Location room1) {
        Player player = new Player(room1);
        player.addAction(new LookAction("look"));
        player.addAction(new ListInventoryAction("inventory"));
        return player;
    }

    default Player createPlayer(Location room1, String name) {
        Player player = new Player(room1,name);
        player.addAction(new LookAction("look"));
        player.addAction(new ListInventoryAction("inventory"));
        return player;
    }

}
