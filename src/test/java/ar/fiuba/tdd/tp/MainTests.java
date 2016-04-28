package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.*;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.actions.*;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.types.CursedItem;
import ar.fiuba.tdd.tp.game.types.EnterRoom;
import ar.fiuba.tdd.tp.game.types.StickGame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTests {

    @Test
    public void dummy_test() {
        assertEquals(0,0);
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
            container.openContainer(room2, player2);
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

    // El jugador no se envenena
    @Test
    public void NoPoison() {
        Room room1 = new Room("Room 1");
        Container container = new Container("Box",1);
        container.noPoison();
        Player player = new Player(room1);
        try {
            container.openContainer(room1, player);
        } catch (Exception e) {
            //Do nothing
        }
        assertTrue(player.getStatus().equals(Player.Status.alive));
    }

    // El jugador se envenena
    @Test
    public void YesPoison() {
        Room room1 = new Room("Room 1");
        Container container = new Container("Box",1);
        container.yesPoison();
        Player player = new Player(room1);
        try {
            container.openContainer(room1, player);
        } catch (Exception e) {
            //Do nothing
        }
        assertTrue(player.getStatus().equals(Player.Status.poisoned));
    }

}
