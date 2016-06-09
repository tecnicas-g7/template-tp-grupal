import game.actions.MoveItemAction;
import game.conditions.PlayerStateCondition;
import game.conditions.RoomCondition;
import game.items.Actionable;
import game.Location;
import game.Player;
import game.states.Status;
import game.tasks.DeadLine;
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


        Player player = createPlayer(room1);
        key.addAction(new MoveItemAction(false,true,"pick"));
        Game game = new Game(player);
        game.makeLocationsAdjacent(room1, room2, key);

        game.addRoom(room1);
        game.addRoom(room2);
        game.addTask(new DeadLine(game),15000,150000);
        game.addLoseCondition(new PlayerStateCondition(new Status("dead")));
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