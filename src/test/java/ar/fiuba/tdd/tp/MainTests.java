package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.*;

import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.types.StickGame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
