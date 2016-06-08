import game.Location;
import game.Player;

import game.actions.MoveItemAction;
import game.conditions.RoomCondition;
import game.items.Actionable;
import model.Game;
import model.GameBuilder;

/**
 * Created by nicol on 18/5/2016.
 */
@SuppressWarnings("CPD-START")
public class EnterRoom implements GameBuilder {

    public Game build() {
        Actionable key = new Actionable("key");


        Location room1 = new Location("Room1");
        room1.addItem(key);
        Location room2 = new Location("Room2");

        makeLocationsAdjacent(room1, room2, key);

        Player player = createPlayer(room1);
        key.addAction(new MoveItemAction(null,player,"pick"));
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

    @SuppressWarnings("CPD-END")

    public String getHelp() {
        return "The player must find the way out of the room.";
    }
}