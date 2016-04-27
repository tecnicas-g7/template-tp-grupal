package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.*;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.SingleItemContainer;
import ar.fiuba.tdd.tp.game.actions.*;
import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.items.Item;
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

    @Test
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
    }
}
