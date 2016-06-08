import game.Location;
import game.Player;
import game.actions.*;
import game.conditions.RoomCondition;
import game.items.Container;
import game.items.Actionable;
import model.Game;
import model.GameBuilder;


/**
 * Created by nicol on 18/5/2016.
 */

@SuppressWarnings("CPD-START")
public class BoxGame implements GameBuilder {

    public Game build() {

        Container box = new Container("box",1);
        //box.addAction(new OpenAction("open"));
        //box.addAction(new CloseAction("close"));
        box.addAction(new OpenCloseContainerAction("open"));
        box.addAction(new OpenCloseContainerAction("close"));


        Location room1 = new Location("Room1");
        room1.addItem(box);
        Actionable key = new Actionable("key");


        box.addComponent(key);
        Location room2 = new Location("Room2");
        makeLocationsAdjacent(room1, room2, key);

        Player player = createPlayer(room1);
        key.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);

        game.addCondition(new RoomCondition(room2,true));

        game.addRoom(room1);
        game.addRoom(room2);

        return game;
    }

    @Override
    public String getName() {
        return "gameBox";
    }


    @SuppressWarnings("CPD-END")
    public String getHelp() {
        return "To leave the room the player must find the hidden key";
    }

}