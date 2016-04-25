package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.model.Door;
import ar.fiuba.tdd.tp.model.Item;
import ar.fiuba.tdd.tp.model.Key;
import ar.fiuba.tdd.tp.model.Player;
import ar.fiuba.tdd.tp.model.Room;
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
        Room room = new Room("Room 1");
        Player player2 = new Player("EstadoGanador",room);
        player2.addToInventory(item);
        room.addItem(item);
        Player player = new Player("Player 1",room);
        System.out.println(room.getListOfItems());
        player.addToInventory(item);
        assertTrue(player.checkVictory(player2));
    }

    @Test
    public void openDoor() {
        Key key = new Key("key");
        Room room1 = new Room("Room 1");
        room1.addItem(key);
        Room room2 = new Room("Room 2");
        Player player = new Player("Player 1",room1);
        room1.addDoor(room2);
        room2.addDoor(room1);
        Door door = room1.getDestinationDoor(room2);
        player.pickKey(key);
        player.openDoor(door);
        player.move(room2,door);
        Player player2 = new Player("EstadoGanador",room2);
        assertTrue(player.checkVictory(player2));
    }
}
