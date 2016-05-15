package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.*;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.types.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTests {

   /* private Item createItemsWithCondition(String nameItem) {
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

        Game stickGame = (new StickGame()).getGame();
        Controller controller = new Controller(stickGame);

        String command = "pick stick";
        controller.interpretCommand(command);

        assertTrue(stickGame.verifyVictory());
    }

    @Test
    public void enterRoom() {

        Game enterRoom = (new EnterRoom()).getGame();
        Controller controller = new Controller(enterRoom);

        String command = "pick key";
        controller.interpretCommand(command);
        String command2 = "enter door1";
        controller.interpretCommand(command2);

        assertTrue(enterRoom.verifyVictory());
    }

    @Test
    public void boxGame() {

        Game boxGame = (new BoxGame()).getGame();
        Controller controller = new Controller(boxGame);

        String command = "open box";
        controller.interpretCommand(command);
        String command2 = "pick key";
        controller.interpretCommand(command2);
        String command3 = "enter door1";
        controller.interpretCommand(command3);

        assertTrue(boxGame.verifyVictory());
    }

    @Test
    public void cursedItem() {

        Game cursedItem = (new CursedItem()).getGame();
        Controller controller = new Controller(cursedItem);

        String command = "pick cursed_item";
        controller.interpretCommand(command);
        String command2 = "enter door1";
        controller.interpretCommand(command2);
        String command3 = "talk thief";
        controller.interpretCommand(command3);
        String command4 = "enter door1";
        controller.interpretCommand(command4);

        assertTrue(cursedItem.verifyVictory());
    }

    @Test
    public void hanoiTower() {

        Game hanoiTower = ( new HanoiTower()).getGame();
        Controller controller = new Controller(hanoiTower);

        String command = "move stack1 stack3";
        controller.interpretCommand(command);
        String command2 = "move stack1 stack2";
        controller.interpretCommand(command2);
        String command3 = "move stack3 stack2";
        controller.interpretCommand(command3);
        String command4 = "move stack1 stack3";
        controller.interpretCommand(command4);
        String command5 = "move stack2 stack1";
        controller.interpretCommand(command5);
        String command6 = "move stack2 stack3";
        controller.interpretCommand(command6);
        String command7 = "move stack1 stack3";
        controller.interpretCommand(command7);

        assertTrue(hanoiTower.verifyVictory());

    }

    @Test
    public void treasure() {
        Game treasureGame = (new TreasureGame()).getGame();
        Controller controller = new Controller(treasureGame);

        String command = "open box";
        controller.interpretCommand(command);
        String command2 = "pick key";
        controller.interpretCommand(command2);
        String command3 = "enter door1";
        controller.interpretCommand(command3);
        String command4 = "enter door2";
        controller.interpretCommand(command4);
        String command5 = "open trunk";
        controller.interpretCommand(command5);
        String command6 = "drop key";
        controller.interpretCommand(command6);
        String command7 = "pick antidote";
        controller.interpretCommand(command7);

        interpretTreasureCommands(controller);


        assertTrue(treasureGame.verifyVictory());

    }

    private void interpretTreasureCommands(Controller controller) {
        String command = "open box";
        controller.interpretCommand(command);
        command = "pick key2";
        controller.interpretCommand(command);
        command = "drink antidote";
        controller.interpretCommand(command);
        command = "enter door1";
        controller.interpretCommand(command);
        command = "enter door3";
        controller.interpretCommand(command);
        command = "enter door2";
        controller.interpretCommand(command);
        command = "pick treasure";
        controller.interpretCommand(command);
    }

    public void makeRoomsAdjacent(Location room1, Location room2, Item key) {
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
        makeRoomsAdjacent(room1, room2, key);
        Door door = room1.getDestinationDoor(room2);
        String command = "pick key";
        try {
            player.openContainer("Box");
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
    public void noPoison() {
        Room room1 = new Room("Room 1");
        Container container = new Container("Box",1);
        container.noPoison();
        Player player = new Player(room1);
        try {
            container.openContainer(player);
        } catch (Exception e) {
            //Do nothing
        }
        assertTrue(player.getStatus().equals(Player.Status.alive));
    }

    // El jugador se envenena
    @Test
    public void yesPoison() {
        Room room1 = new Room("Room 1");
        Container container = new Container("Box",1);
        container.yesPoison();
        Player player = new Player(room1);
        try {
            container.openContainer(player);
        } catch (Exception e) {
            //Do nothing
        }
        assertTrue(player.getStatus().equals(Player.Status.poisoned));
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
        Item key = new Item("key");
        Room room1 = new Room("Room 1");
        Container container = new Container("Box",1);
        container.addComponent(key);
        room1.addContainerComponent(container);
        Container container1 = new Container("Baul",10);
        Container container2 = new Container("Box2",1);
        Item stick = new Item("Stick");
        container2.addComponent(stick);
        container1.addComponent(container2);
        room1.addContainerComponent(container1);
        Player player = new Player(room1);
        player.openContainer("Baul");
        System.out.println(room1.look());
        assertTrue(true);
    }


    private void simpleCross(Controller controller,  String animal, String moveTo) {
        controller.interpretCommand("take " + animal);
        controller.interpretCommand("cross " + moveTo);
        controller.interpretCommand("leave " + animal);
    }

    @Test
    public void riverCrossingVictory() {

        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);

        simpleCross(controller,"sheep", "north-shore");

        controller.interpretCommand("cross south-shore");

        simpleCross(controller, "wolf", "north-shore");

        controller.interpretCommand("take sheep");
        controller.interpretCommand("cross south-shore");
        controller.interpretCommand("leave sheep");


        simpleCross(controller,"cabbage", "north-shore");

        controller.interpretCommand("cross south-shore");

        simpleCross(controller, "sheep", "north-shore");


        assertTrue(riverCrossing.verifyVictory());

    }

    @Test
    public void riverCrossingFail() {

        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);

        String takeSheep = "take sheep";
        String leaveSheep = "leave sheep";

        String takeWolf = "take wolf";
        String leaveWolf = "leave wolf";

        String northShore = "cross north-shore";
        String crossSouth = "cross south-shore";

        controller.interpretCommand(takeSheep);
        controller.interpretCommand(northShore);
        controller.interpretCommand(leaveSheep);

        controller.interpretCommand(crossSouth);

        controller.interpretCommand(takeWolf);
        controller.interpretCommand(northShore);
        controller.interpretCommand(leaveWolf);


        assertTrue(!riverCrossing.verifyVictory());
    }

    @Test
    public void moreItemsThaAllowed() {
        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);

        String takeSheep = "take sheep";
        String takeWolf = "take wolf";
        controller.interpretCommand(takeSheep);
        controller.interpretCommand(takeWolf);
        Assert.assertTrue(riverCrossing.getPlayer().getInventory().size() == 1);
    }

    @Test
    public void getWrongItem() {
        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);
        String takeWrongItem = "take wrongItem";
        controller.interpretCommand(takeWrongItem);
        Assert.assertTrue(riverCrossing.getPlayer().getInventory().isEmpty());
    }

    @Test
    public void forbiddenMove() {

        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);

        String takeCabbage = "take cabbage";
        String northShore = "cross north-shore";


        controller.interpretCommand(takeCabbage);
        controller.interpretCommand(northShore);
        //No se pudo mover.
        Assert.assertTrue(riverCrossing.getPlayer().getRoom().getName().equals("south-shore"));
    }

*/
}
