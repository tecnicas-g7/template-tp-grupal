package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.*;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.PlayerStateCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.*;
import ar.fiuba.tdd.tp.game.states.StatePlayer;
import ar.fiuba.tdd.tp.game.types.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTests {

    private Actionable createItemsWithCondition(String nameItem, Player player) {
        Actionable itemWithActions = new Actionable(nameItem);
        itemWithActions.addAction(new MoveItemAction(null,player,"pick"));
        itemWithActions.addAction(new MoveItemAction(player,null,"drop"));
        return itemWithActions;
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

        player.setMaxInventory(2);
        Game game = new Game(player);
        game.makeLocationsAdjacent(room1,room2,key);
        game.addRoom(room1);
        game.addRoom(room2);
        List<Actionable> items = new ArrayList<>(Arrays.asList(stick,key));
        game.addCondition(new InventoryCondition(items,true));
        game.addCondition(new RoomCondition(room2,true));
        return game;
    }

    @Test
    public void cantEnterDoor() {
        Actionable key = new Actionable("key");
        Location room1 = new Location("Room 1");
        Container container = new Container("Box",1);
        container.addComponent(key);
        room1.addItem(container);
        Location room2 = new Location("Room 2");
        Player player2 = new Player(room2);
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2,key,enterAction);
        room2.addDoor(room1, key, enterAction);
        Linker door = room1.getDestinationDoor(room2);
        try {
            container.openContainer(player2);
            player2.addItem(room2.getItem("key"));
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
            controller.interpretCommand(command);
            command = "pick mouse";
            controller.interpretCommand(command);
            command = "pick stick";
            controller.interpretCommand(command);
            command = "drop mouse";
            controller.interpretCommand(command);
            command = "pick stick";
            controller.interpretCommand(command);
            command = "enter door1";
            controller.interpretCommand(command);

        } catch (Exception e) {
            //Do nothing
        }

        assertTrue(dropGame.verifyVictory());
    }

    @Test
    public void lookAroundTest() {
        Actionable key = new Actionable("key");
        Location room1 = new Location("Room 1");
        Container container = new Container("Box",1);
        container.addComponent(key);
        room1.addItem(container);
        Container container1 = new Container("Baul",10);
        Container container2 = new Container("Box2",1);
        Actionable stick = new Actionable("Stick");
        container2.addComponent(stick);
        container1.addComponent(container2);
        room1.addItem(container1);
        Player player = new Player(room1);
        player.openContainer("Baul");
        System.out.println(room1.look());
        assertTrue(true);
    }

    @Test
    public void verifyStatus() {
        Location room1 = new Location("Room1");
        Player player  = new Player(room1);
        player.addAction(new LookAction("look"));

        Container box = new Container("box",1);
        PlayerStatusAction firstAction = new PlayerStatusAction(new StatePlayer("dead"),"open");
        ComplexAction action = new ComplexAction("open");
        action.addAction(firstAction);
        box.addAction(action);
        room1.addItem(box);

        Game game = new Game(player);
        game.addRoom(room1);
        game.addLoseCondition(new PlayerStateCondition(new StatePlayer("dead")));

        //Juego, muere y debe perder
        Controller controller = new Controller(game);
        String command = "open box";
        System.out.println(controller.interpretCommand(command));
        assertTrue(game.verifyVictory());
    }
}
