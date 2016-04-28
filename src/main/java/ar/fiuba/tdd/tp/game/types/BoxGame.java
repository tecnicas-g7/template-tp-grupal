package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Container;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.actions.CloseAction;
import ar.fiuba.tdd.tp.game.actions.OpenAction;
import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by fran on 27/04/16.
 */
public class BoxGame {

    public static Game getGame() {
        Item key = new Item("key");
        key.addAction(new PickAction());
        Container box = new Container("box",1);
        box.addAction(new OpenAction());
        box.addAction(new CloseAction());

        Room room1 = new Room("Room1");
        room1.addContainerComponent(box);
        box.addComponent(key);
        Room room2 = new Room("Room2");
        room1.addDoor(room2,key);
        room2.addDoor(room1,key);

        Player player = new Player(room1);

        Game game = new Game(player);

        game.addCondition(new RoomCondition(room2,true));

        game.addRoom(room1);
        game.addRoom(room2);

        return game;
    }
}
