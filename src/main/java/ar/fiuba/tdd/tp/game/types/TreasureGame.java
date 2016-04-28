package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.actions.*;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.PlayerStateCondition;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 Created by fran on 28/04/16.
 */
public class TreasureGame {

    public static Game getGame() {
        Room room1 = new Room("Room1");
        Room room2 = new Room("Room2");

        createComponentsFirstRoom(room1, room2);

        Room room3 = new Room("Room3");
        Room room4 = new Room("Room4");
        Room room5 = new Room("Room5");

        createComponentsSecondRoom(room2, room3, room4);
        createComponentsThirdRoom(room3, room4, room5);

        Item treasure = new Item("treasure");

        createComponentsFifthRoom(room5, treasure);

        Player player = new Player(room1);
        player.setMaxInventory(2);

        Game game = new Game(player);

        addRooms(game, room1, room2, room3, room4, room5);

        List<ContainerComponent> items = new ArrayList<>();
        items.add(treasure);

        game.addCondition(new InventoryCondition(items, true));

        return game;
    }

    private static void addRooms(Game game, Room room1, Room room2, Room room3, Room room4, Room room5) {
        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);
        game.addRoom(room4);
        game.addRoom(room5);
    }

    private static void createComponentsFirstRoom(Room room1, Room room2) {
        Item key = new Item("key");
        addPickDrop(key);
        Container box = new Container("box",1);
        addOpenClose(box);
        box.addComponent(key);
        room1.addContainerComponent(box);
        room1.addDoor(room2, key);
        room2.addDoor(room1, null);
    }

    private static void createComponentsSecondRoom(Room room2, Room room3, Room room4) {
        room2.addDoor(room3, null);
        room2.addDoor(room4, null);
        room3.addDoor(room2,null);
        room4.addDoor(room2,null);
    }

    private static void createComponentsThirdRoom(Room room3, Room room4, Room room5) {
        Container trunk = new Container("trunk", 10);
        addOpenClose(trunk);
        Item antidote = new Item("antidote");
        addPickDrop(antidote);
        antidote.addAction(new DrinkAction());
        Container box = new Container("box",1);
        addOpenClose(box);
        Item key = new Item("key2");
        addPickDrop(key);
        box.addComponent(key);
        box.yesPoison();
        trunk.addComponent(antidote);
        trunk.addComponent(box);
        room3.addContainerComponent(trunk);
        room4.addDoor(room5, key);
        room5.addDoor(room4,null);
        room3.addLeaveCondition(new PlayerStateCondition(Player.Status.poisoned));
    }

    private static void createComponentsFifthRoom(Room room5, Item treasure) {
        addPickDrop(treasure);
        room5.addContainerComponent(treasure);
    }

    private static void addPickDrop(Item item) {
        item.addAction(new PickAction());
        item.addAction(new DropAction());
    }

    private static void addOpenClose(Container container) {
        container.addAction(new OpenAction());
        container.addAction(new CloseAction());
    }

    public static String getHelp() {
        return "The player will look for the treasure!";
    }

}
