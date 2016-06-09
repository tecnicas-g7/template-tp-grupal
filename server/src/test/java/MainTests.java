import game.Controller;
import game.Location;
import game.Player;
import game.actions.EnterAction;
import game.actions.MoveItemAction;
import game.conditions.InventoryCondition;
import game.conditions.RoomCondition;
import game.items.Actionable;
import game.items.Container;
import game.items.Linker;
import model.Game;
import org.junit.Test;
import server.driver.Driver;
import server.driver.GameDriver;
import server.GamePaths;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("CPD-START")
public class MainTests {

    private Actionable createItemsWithCondition(String nameItem, Player player) {
        Actionable itemWithActions = new Actionable(nameItem);
        itemWithActions.addAction(new MoveItemAction(false, true, "pick"));
        itemWithActions.addAction(new MoveItemAction(true,false, "drop"));
        return itemWithActions;
    }

    private void makeLocationsAdjacent(Location room1, Location room2, Actionable key) {
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2, key, enterAction);
        room2.addDoor(room1, key, enterAction);
    }

    private Game getGameCondition() {
        Location room1 = new Location("Room1");
        Player player = new Player(room1);
        Actionable key = createItemsWithCondition("key", player);
        Actionable mouse = createItemsWithCondition("mouse", player);
        Actionable stick = createItemsWithCondition("stick", player);

        room1.addItem(key);
        room1.addItem(mouse);
        room1.addItem(stick);
        Location room2 = new Location("Room2");
        makeLocationsAdjacent(room1, room2, key);

        player.setMaxInventory(2);
        Game game = new Game(player);
        game.addRoom(room1);
        game.addRoom(room2);
        List<Actionable> items = new ArrayList<>(Arrays.asList(stick, key));
        game.addCondition(new InventoryCondition(items, true));
        game.addCondition(new RoomCondition(room2, true));
        return game;
    }


    @Test
    public void cantTakeMoreItems() {
        Game dropGame = getGameCondition();
        Controller controller = new Controller(dropGame);
        try {
            String command = "pick key";
            controller.interpretCommand(command);
            command = "pick mouse";
            controller.interpretCommand((command));
            command = "pick stick";
            controller.interpretCommand((command));
            command = "drop mouse";
            controller.interpretCommand((command));
            command = "pick stick";
            controller.interpretCommand((command));
            command = "enter door1";
            controller.interpretCommand((command));

        } catch (Exception e) {
            //Do nothing
        }

        assertTrue(dropGame.verifyVictory());
    }


}
