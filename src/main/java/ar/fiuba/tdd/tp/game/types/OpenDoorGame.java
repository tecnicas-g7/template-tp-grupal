package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.conditions.Condition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by fran on 26/04/16.
 */
public class OpenDoorGame {

    public static Game getGame() {
        Item key = new Item("key");
        key.addAction(new PickAction());

        Room room1 = new Room("Room 1");
        room1.addItem(key);
        Room room2 = new Room("Room 2");

        Player player = new Player(room1);

        room1.addDoor(room2, key);
        room2.addDoor(room1, key);

        Game game = new Game(player);

        game.addRoom(room1);
        game.addRoom(room2);

        Condition condition = new RoomCondition(room2,true);
        game.addCondition(condition);

        return game;
    }
}
