package configuration;
import game.Location;
import game.Player;
import game.actions.CloseAction;
import game.actions.EnterAction;
import game.actions.MoveItemAction;
import game.actions.OpenAction;
import game.conditions.RoomCondition;
import game.items.Container;
import game.items.Item;
import model.Game;
import model.GameBuilder;

/**
 * Created by nicol on 18/5/2016.
 */
public class Configuration  implements GameBuilder {

    public Game build() {

        Container box = new Container("box",1);
        box.addAction(new OpenAction("open"));
        box.addAction(new CloseAction("close"));

        Location room1 = new Location("Room1");
        room1.addItem(box);
        Item key = new Item("key");

        box.addComponent(key);
        Location room2 = new Location("Room2");
        EnterAction enterAction = new EnterAction("enter");
        //room1.addDoor(room2,key,enterAction);
//        room2.addDoor(room1,key,enterAction);

        Player player = new Player(room1);
        key.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);

        game.addCondition(new RoomCondition(room2,true));

        game.addRoom(room1);
        game.addRoom(room2);

        return game;
    }

    public String getName() {
        return "gameBox";
    }

    public String getHelp() {
        return "To leave the room the player must find the hidden key";
    }
	
}
