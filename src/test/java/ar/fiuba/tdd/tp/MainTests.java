package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.actions.*;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.types.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        room1.addContainerComponent(key);
        room1.addContainerComponent(mouse);
        room1.addContainerComponent(stick);
        Room room2 = new Room("Room2");
        Player player = new Player(room1);
        player.setMaxInventory(2);
        room1.addDoor(room2,key);
        room2.addDoor(room1,key);
        Game game = new Game(player);
        game.addRoom(room1);
        game.addRoom(room2);
        List<ContainerComponent> items = new ArrayList<>(Arrays.asList(stick,key));
        // items.add(stick);
        //  items.add(key);
        game.addCondition(new InventoryCondition(items,true));
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
    public void boxGame() {

        Game boxGame = BoxGame.getGame();
        Controller controller = new Controller(boxGame);

        String command = "open box";
        controller.interptetCommand(command);
        String command2 = "pick key";
        controller.interptetCommand(command2);
        String command3 = "enter door1";
        controller.interptetCommand(command3);

        assertTrue(boxGame.verifyVictory());
    }

    @Test
    public void cursedItem() {

        Game cursedItem = CursedItem.getGame();
        Controller controller = new Controller(cursedItem);

        String command = "pick cursed_item";
        controller.interptetCommand(command);
        String command2 = "enter door1";
        controller.interptetCommand(command2);
        String command3 = "talk thief";
        controller.interptetCommand(command3);
        String command4 = "enter door1";
        controller.interptetCommand(command4);

        assertTrue(cursedItem.verifyVictory());
    }

    @Test
    public void hanoiTower() {

        Game hanoiTower = HanoiTower.getGame();
        Controller controller = new Controller(hanoiTower);

        String command = "move stack1 stack3";
        controller.interptetCommand(command);
        String command2 = "move stack1 stack2";
        controller.interptetCommand(command2);
        String command3 = "move stack3 stack2";
        controller.interptetCommand(command3);
        String command4 = "move stack1 stack3";
        controller.interptetCommand(command4);
        String command5 = "move stack2 stack1";
        controller.interptetCommand(command5);
        String command6 = "move stack2 stack3";
        controller.interptetCommand(command6);
        String command7 = "move stack1 stack3";
        controller.interptetCommand(command7);

        assertTrue(hanoiTower.verifyVictory());

    }

    public void makeRoomsAdjacent(Room room1, Room room2, Item key) {
        room1.addDoor(room2, key);
        room2.addDoor(room1, key);
    }

    @Test
    public void openDoor2() {
        Item key = new Item("key");
        key.addAction(new PickAction());
        Room room1 = new Room("Room 1");
        Container container = new Container("Box",1);
        container.addComponent(key);
        room1.addContainerComponent(container);
        Room room2 = new Room("Room 2");
        Player player = new Player(room1);
        Player player2 = new Player(room2);
        makeRoomsAdjacent(room1,room2, key);
        Door door = room1.getDestinationDoor(room2);
        String command = "pick key";
        try {
            player.openRoomContainer("Box");
            key.executeAction(command.split(" "), player);
        } catch (Exception e) {
            //Do nothing
        }
        try {
            player2.addItem(key);
        } catch (Exception e) {
            //Do nothing
        }
        player.enter(door);
        assertTrue(player.checkVictory(player2));
    }

    @Test
    public void cantEnterDoor() {
        Item key = new Item("key");
        Room room1 = new Room("Room 1");
        Container container = new Container("Box",1);
        container.addComponent(key);
        room1.addContainerComponent(container);
        Room room2 = new Room("Room 2");
        Player player2 = new Player(room2);
        room1.addDoor(room2,key);
        room2.addDoor(room1,key);
        Door door = room1.getDestinationDoor(room2);
        try {
            container.openContainer(room2);
            player2.addItem(room2.getItem("key"));
        } catch (Exception e) {
            //Do nothing
        }
        Player player = new Player(room1);
        player.enter(door);
        assertFalse(player.checkVictory(player2));
    }

    @Test
    public void takeAntidoteWhilePoisoned() {
        Item antidote = new Item("antidote");
        antidote.addAction(new DrinkAction());
        Room room = new Room("room");
        Player player = new Player(room);
        player.changeStatus(Player.Status.poisoned);
        String command = "drink antidote";
        try {
            antidote.executeAction(command.split(" "), player);
        } catch (Exception e) {
            //Do nothing
        }
        assertTrue(Player.Status.alive == player.getStatus());
    }

    @Test
    public void takeAntidoteWhileNotPoisoned() {
        Item antidote = new Item("antidote");
        antidote.addAction(new DrinkAction());
        Room room = new Room("room");
        Player player = new Player(room);
        String command = "drink antidote";
        try {
            antidote.executeAction(command.split(" "), player);
        } catch (Exception e) {
            //Do nothing
        }
        assertTrue(Player.Status.alive == player.getStatus());
    }

    /*@Test
    public void poison() {
        Item poison = new Item("poison");
        poison.addAction(new PoisonAction());
        Room room1 = new Room("Room 1");
        room1.addItem(poison);
        Player player = new Player(room1);
        room1.addDoor(room1,poison);
        String command = "poison poison";
        try {
            poison.executeAction(command.split(" "), player);
        } catch (Exception e) {
            //Do nothing
        }
        assertTrue(player.getStatus().equals(Player.Status.poisoned));
    }*/

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
