package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by fran on 24/04/16.
 */
public class StickGame {

    public static Game getGame() {
        Room room = new Room("room");

        Item stick = new Item("stick");
        stick.addAction(new PickAction());
        room.addItem(stick);

        Player player = new Player(room);

        Game game = new Game(player);

        game.addRoom(room);

        return game;
    }
}
