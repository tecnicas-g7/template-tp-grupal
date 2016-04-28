package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.actions.*;
import ar.fiuba.tdd.tp.game.conditions.ItemTypeCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomWithItemsCondition;
import ar.fiuba.tdd.tp.game.items.Item;

import ar.fiuba.tdd.tp.game.items.type.CarnivorousType;
import ar.fiuba.tdd.tp.game.items.type.HerbivorousType;
import ar.fiuba.tdd.tp.game.items.type.PlantType;


import java.util.ArrayList;
import java.util.List;

/**
 Created by Nico on 23/4/2016.
 */


public class RiverCrossing {

    public static Game getGame() {

        Room leftShore = new Room("south-shore");
        Room rightShore = new Room("north-shore");

        leftShore.addDoor(rightShore, null, "north-shore");
        rightShore.addDoor(leftShore, null, "south-shore");

        leftShore.addEnterCondition(new ItemTypeCondition());
        leftShore.addLeaveCondition(new ItemTypeCondition());

        rightShore.addEnterCondition(new ItemTypeCondition());
        rightShore.addLeaveCondition(new ItemTypeCondition());

        Player player = new Player(leftShore);
        player.setMaxInventory(1);

        createComponents().forEach(item -> leftShore.addContainerComponent(item));

        Game game = new Game(player);

        game.addRoom(leftShore);
        game.addRoom(rightShore);

        game.addCondition(setWinCondition(rightShore));

        return game;
    }

    private static List<Item> createComponents() {

        Item sheep = new Item("sheep");
        sheep.setType(new HerbivorousType());
        sheep = addActions(sheep);

        Item wolf = new Item("wolf");
        wolf.setType(new CarnivorousType());
        wolf = addActions(wolf);

        Item cabbage = new Item("cabbage");
        cabbage.setType(new PlantType());
        cabbage = addActions(cabbage);

        List<Item> items = new ArrayList<>();
        items.add(sheep);
        items.add(wolf);
        items.add(cabbage);

        return items;
    }

    private static Item addActions(Item item) {
        PickAction take = new PickAction("take");
        DropAction leave = new DropAction("leave");
        item.addAction(take);
        item.addAction(leave);
        return item;
    }

    private static RoomWithItemsCondition setWinCondition(Room room) {
        return new RoomWithItemsCondition(createComponents(), room);
    }

    public static String getHelp() {
        return "Your goal is to bring the wolf, the sheep, and the head of cabbage to the left side of the river in the man's boat.";
    }
}
