package configuration;

import game.Location;
import game.Player;
import game.actions.EnterAction;
import game.actions.MoveItemAction;
import game.conditions.RoomCondition;
import game.items.Item;
import model.Game;
import model.GameBuilder;

/**
 * Created by nicol on 18/5/2016.
 */
public class Configuration implements GameBuilder {

    public Game build() {

        Item key = new Item("key");


        Location room1 = new Location("Room1");
        room1.addItem(key);
        Location room2 = new Location("Room2");

        Player player = new Player(room1);
        key.addAction(new MoveItemAction(null,player,"pick"));
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2,key,enterAction);
        room2.addDoor(room1,key,enterAction);
        Game game = new Game(player);

        game.addRoom(room1);
        game.addRoom(room2);

        game.addCondition(new RoomCondition(room2,true));

        return game;
    }

    @Override
    public String getName() {
        return "enterRoom";
    }

    public String getHelp() {
        return "The player must find the way out of the room.";
    }

}
