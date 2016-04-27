package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.actions.DropAction;
import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.types.EnterRoom;
import ar.fiuba.tdd.tp.game.types.StickGame;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTests {

    private Item createItemsWithCondition(String nameItem) {
        Item itemWithActions = new Item(nameItem);
        itemWithActions.addAction(new PickAction());
        itemWithActions.addAction(new DropAction());
        return itemWithActions;
    }

    private Game getGameCondition() {
        Item key = createItemsWithCondition("key");
        Item mouse = createItemsWithCondition("mouse");
        Item stick = createItemsWithCondition("stick");
        Room room1 = new Room("Room1");
        room1.addItem(key);
        room1.addItem(mouse);
        room1.addItem(stick);
        Room room2 = new Room("Room2");
        Player player = new Player(room1);
        player.setMaxInventory(2);
        room1.addDoor(room2,key);
        room2.addDoor(room1,key);
        Game game = new Game(player);
        game.addRoom(room1);
        game.addRoom(room2);
        List<Item> items = new ArrayList<>(Arrays.asList(stick,key));
        // items.add(stick);
        //  items.add(key);
        game.addCondition(new InventoryCondition(items));
        game.addCondition(new RoomCondition(room2,true));
        return game;
    }

    @Test
    public void stickQuest() {

        Game stickGame = StickGame.getGame();
        Controller controller = new Controller(stickGame);

        String command = "pick stick";
        controller.interptetCommand(command);

        assertTrue(stickGame.verifyVictory());
    }

    @Test
    public void enterRoom() {

        Game enterRoom = EnterRoom.getGame();
        Controller controller = new Controller(enterRoom);

        String command = "pick key";
        controller.interptetCommand(command);
        String command2 = "enter door1";
        controller.interptetCommand(command2);
        
        assertTrue(enterRoom.verifyVictory());
    }

    @Test
    public void openDoor2() {
        Item key = new Item("key");
        key.addAction(new PickAction());
        Room room1 = new Room("Room 1");
        SingleItemContainer container = new SingleItemContainer("Box");
        container.setItem(key);
        room1.addSimpleContainer(container);
        Room room2 = new Room("Room 2");
        Player player = new Player(room1);
        Player player2 = new Player(room2);
        room1.addDoor(room2,key);
        room2.addDoor(room1,key);
        Door door = room1.getDestinationDoor(room2);
        String command = "pick key";
        try {
            key.executeAction(command.split(" "), player);
        } catch (Exception e) {
            //Do nothing
        }
        try {
            player2.addItem(container.openContainer());
        } catch (Exception e) {
            //Do nothing
        }

        player.enter(door);

        assertTrue(player.checkVictory(player2));
    }

    @Test
    public void cantEnterDoor() {
        Item key = new Item("key");
        key.addAction(new PickAction());
        Room room1 = new Room("Room 1");
        SingleItemContainer container = new SingleItemContainer("Box");
        container.setItem(key);
        room1.addSimpleContainer(container);
        Room room2 = new Room("Room 2");
        Player player2 = new Player(room2);
        room1.addDoor(room2,key);
        room2.addDoor(room1,key);
        Door door = room1.getDestinationDoor(room2);
        String command = "pick key";
        try {
            player2.addItem(container.openContainer());
        } catch (Exception e) {
            //Do nothing
        }

        Player player = new Player(room1);
        player.enter(door);

        assertFalse(player.checkVictory(player2));
    }

    @Test
    public void cantTakeMoreItems() {
        Game dropGame = getGameCondition();
        Controller controller = new Controller(dropGame);
        try {
            String command = "pick key";
            controller.interptetCommand(command);
            command = "pick mouse";
            controller.interptetCommand(command);
            command = "pick stick";
            controller.interptetCommand(command);
            command = "drop mouse";
            controller.interptetCommand(command);
            command = "pick stick";
            controller.interptetCommand(command);
            command = "enter door1";
            controller.interptetCommand(command);

        } catch (Exception e) {
            //Do nothing
        }

        assertTrue(dropGame.verifyVictory());
    }
}
