import game.Location;
import game.Player;
import game.actions.ClearInventoryAction;
import game.actions.EnterAction;
import game.actions.MoveItemAction;
import game.conditions.InventoryCondition;
import game.conditions.PlayerStateCondition;
import game.conditions.RoomCondition;
import game.items.Actionable;
import game.states.State;
import game.states.StatePlayer;
import game.tasks.DeadLine;
import game.tasks.ScheduledTask;
import game.utils.Messages;
import model.Game;
import model.GameBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicol on 18/5/2016.
 */
@SuppressWarnings("CPD-START")
public class CursedItem implements GameBuilder {

    public Game build() {
        Location room1 = new Location("Room1");
        Location room2 = new Location("Room2");

        Actionable cursedItem = new Actionable("cursed_item");


        room1.addItem(cursedItem);
        createItemsSecondRoom(room2);

        Location room3 = new Location("Room3");

        addRoomConditions(room2,room3,cursedItem);
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2,null,enterAction);

        room2.addDoor(room3,null,enterAction);

        Player player = createPlayer(room1);
        cursedItem.addAction(new MoveItemAction(false,true,"pick"));
        Game game = new Game(player);

        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);

        game.addCondition(new RoomCondition(room3, true));
        game.addLoseCondition(new PlayerStateCondition(new StatePlayer("dead")));
        game.addTask(new DeadLine(game),60000,150000);

        return game;
    }

    @Override
    public String getName() {
        return "cursedItem";
    }

    private static void createItemsSecondRoom(Location room) {
        Actionable thief = new Actionable("thief");

        String stealMessage = "Oh no! The " + thief.getName() + " " + Messages.getMessage("hasStolenYourItems");
        thief.addAction(new ClearInventoryAction("talk", stealMessage));

        room.addItem(thief);
    }

    private static void addRoomConditions(Location room2, Location room3, Actionable cursedItem) {
        List<Actionable> items = new ArrayList<>();
        items.add(cursedItem);

        room2.addEnterCondition(new InventoryCondition(items, true));
        room3.addEnterCondition(new InventoryCondition(items, false));
    }

    @SuppressWarnings("CPD-END")

    public String getHelp() {
        return "The player needs to find the cursed artifact and get rid of it somehow to win";
    }
}