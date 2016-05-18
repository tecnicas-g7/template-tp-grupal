package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.actions.CloseAction;
import ar.fiuba.tdd.tp.game.actions.MoveItemAction;
import ar.fiuba.tdd.tp.game.actions.OpenAction;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Container;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 Created by fran on 27/04/16.
 */
public class BoxGame implements GameFactory {

    public Game getGame() {

        Container box = new Container("box",1);
        box.addAction(new OpenAction("open"));
        box.addAction(new CloseAction("close"));

        Location room1 = new Location("Room1");
        room1.addItem(box);
        Item key = new Item("key");


        box.addComponent(key);
        Location room2 = new Location("Room2");
        room1.addDoor(room2,key);
        room2.addDoor(room1,key);

        Player player = new Player(room1);
        key.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);

        game.addCondition(new RoomCondition(room2,true));

        game.addRoom(room1);
        game.addRoom(room2);

        return game;
    }

    public String getHelp() {
        return "To leave the room the player must find the hidden key";
    }

}
