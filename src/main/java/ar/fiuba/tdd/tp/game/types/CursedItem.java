package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.actions.ThiefAction;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fran on 27/04/16.
 */
public class CursedItem {

    public static Game getGame() {
        Room room1 = new Room("Room1");
        Room room2 = new Room("Room2");

        Item cursedItem = new Item("cursed_item");
        cursedItem.addAction(new PickAction());

        room1.addContainerComponent(cursedItem);
        createItemsSecondRoom(room2);

        Room room3 = new Room("Room3");

        addRoomConditions(room2,room3,cursedItem);

        Player player = new Player(room1);

        room1.addDoor(room2,null);

        room2.addDoor(room3,null);

        Game game = new Game(player);

        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);

        game.addCondition(new RoomCondition(room3, true));

        return game;
    }

    private static void createItemsSecondRoom(Room room) {
        Item thief = new Item("thief");
        thief.addAction(new ThiefAction());

        room.addContainerComponent(thief);
    }

    private static void addRoomConditions(Room room2, Room room3, ContainerComponent cursedItem) {
        List<ContainerComponent> items = new ArrayList<>();
        items.add(cursedItem);

        room2.addEnterCondition(new InventoryCondition(items, true));
        room3.addEnterCondition(new InventoryCondition(items, false));
    }


}