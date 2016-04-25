package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Door;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.SingleItemContainer;
import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.items.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTests {

    @Test
    public void dummy_test() {
        assertEquals(0,0);
    }

    @Test
    public void fetchQuest() {
        Item item = new Item("Stick");
        item.addAction(new PickAction());

        Room room = new Room("Room 1");

        Player player = new Player(room);
        Player player2 = new Player(room);

        String command = "pick stick";

        try {
            item.executeAction(command.split(" "), player);
        } catch (Exception e) {
            //Do nothing
        }
        try {
            player2.addItem(item);
        } catch (Exception e) {
            //Do nothing
        }

        room.addItem(item);

        assertTrue(player.checkVictory(player2));
    }

    @Test
    public void openDoor() {
        Item key = new Item("key");
        key.addAction(new PickAction());
        Room room1 = new Room("Room 1");
        room1.addItem(key);
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
            player2.addItem(key);
        } catch (Exception e) {
            //Do nothing
        }

        player.move(room2,door);

        assertTrue(player.checkVictory(player2));
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

        player.move(room2,door);

        assertTrue(player.checkVictory(player2));
    }
}
