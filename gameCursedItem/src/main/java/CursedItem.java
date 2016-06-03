
import game.Location;
import game.Player;
import game.actions.EnterAction;
import game.actions.MoveItemAction;
import game.actions.ThiefAction;
import game.conditions.InventoryCondition;
import game.conditions.RoomCondition;
import game.items.Actionable;
import game.items.Item;
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

        Item cursedItem = new Item("cursed_item");


        room1.addItem(cursedItem);
        createItemsSecondRoom(room2);

        Location room3 = new Location("Room3");

        addRoomConditions(room2,room3,cursedItem);
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2,null,enterAction);

        room2.addDoor(room3,null,enterAction);

        Player player = createPlayer(room1);
        cursedItem.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);

        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);

        game.addCondition(new RoomCondition(room3, true));

        return game;
    }

    @Override
    public String getName() {
        return "cursedItem";
    }

    private static void createItemsSecondRoom(Location room) {
        Item thief = new Item("thief");
        thief.addAction(new ThiefAction("talk"));

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