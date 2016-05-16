package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Item;

/*
Created by fran on 24/04/16.
*/

public class EnterRoom implements GameFactory {

    public Game getGame() {
        Item key = new Item("key");
        key.addAction(new PickAction());

        Location room1 = new Location("Room1");
        room1.addDescribable(key);
        Location room2 = new Location("Room2");

        Player player = new Player(room1);

        room1.addDoor(room2,key);
        room2.addDoor(room1,key);
        Game game = new Game(player);

        game.addRoom(room1);
        game.addRoom(room2);

        game.addCondition(new RoomCondition(room2,true));

        return game;
    }

    public String getHelp() {
        return "The player must find the way out of the room.";
    }
}
