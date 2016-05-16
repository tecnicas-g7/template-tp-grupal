package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.actions.PickAction;
import ar.fiuba.tdd.tp.game.actions.ThiefAction;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.ArrayList;
import java.util.List;

/*
Created by fran on 27/04/16.
*/

public class CursedItem implements GameFactory {

    public Game getGame() {
        Location room1 = new Location("Room1");
        Location room2 = new Location("Room2");

        Item cursedItem = new Item("cursed_item");
        cursedItem.addAction(new PickAction());

        room1.addDescribable(cursedItem);
        createItemsSecondRoom(room2);

        Location room3 = new Location("Room3");

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

    private static void createItemsSecondRoom(Location room) {
        Item thief = new Item("thief");
        thief.addAction(new ThiefAction());

        room.addDescribable(thief);
    }

    private static void addRoomConditions(Location room2, Location room3, Describable cursedItem) {
        List<Describable> items = new ArrayList<>();
        items.add(cursedItem);

        room2.addEnterCondition(new InventoryCondition(items, true));
        room3.addEnterCondition(new InventoryCondition(items, false));
    }

    public String getHelp() {
        return "The player needs to find the cursed artifact and get rid of it somehow to win";
    }

}
