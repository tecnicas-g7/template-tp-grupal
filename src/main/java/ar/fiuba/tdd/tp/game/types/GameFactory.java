package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.EnterAction;
import ar.fiuba.tdd.tp.game.actions.ListInventoryAction;
import ar.fiuba.tdd.tp.game.actions.LookAction;
import ar.fiuba.tdd.tp.game.items.Item;

public interface GameFactory {

    Game getGame();

    String getHelp();

    default void makeLocationsAdjacent(Location room1, Location room2, Item key) {
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
}
