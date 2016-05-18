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


public class RiverCrossing implements GameFactory {

    public Game getGame() {

        Location leftShore = new Location("south-shore");
        Location rightShore = new Location("north-shore");
        EnterAction crossAction = new EnterAction("cross");
        leftShore.addDoor(rightShore, null, "north-shore",crossAction);
        rightShore.addDoor(leftShore, null, "south-shore",crossAction);

        leftShore.addEnterCondition(new ItemTypeCondition());
        leftShore.addLeaveCondition(new ItemTypeCondition());

        rightShore.addEnterCondition(new ItemTypeCondition());
        rightShore.addLeaveCondition(new ItemTypeCondition());

        Player player = new Player(leftShore);
        player.setMaxInventory(1);

        createComponents(player).forEach(item -> leftShore.addItem(item));

        Game game = new Game(player);

        game.addRoom(leftShore);
        game.addRoom(rightShore);

        game.addCondition(setWinCondition(rightShore,player));

        return game;
    }

    private static List<Item> createComponents(Player player) {

        Item sheep = new Item("sheep");
        sheep.setType(new HerbivorousType());
        sheep = addActions(sheep,player);

        Item wolf = new Item("wolf");
        wolf.setType(new CarnivorousType());
        wolf = addActions(wolf,player);

        Item cabbage = new Item("cabbage");
        cabbage.setType(new PlantType());
        cabbage = addActions(cabbage,player);

        List<Item> items = new ArrayList<>();
        items.add(sheep);
        items.add(wolf);
        items.add(cabbage);

        return items;
    }

    private static Item addActions(Item item, Player player) {
        MoveItemAction take = new MoveItemAction(null,player,"take");
        MoveItemAction leave = new MoveItemAction(player,null,"leave");
        item.addAction(take);
        item.addAction(leave);
        return item;
    }

    private static RoomWithItemsCondition setWinCondition(Location room, Player player) {
        return new RoomWithItemsCondition(createComponents(player), room);
    }

    public String getHelp() {
        return "Your goal is to bring the wolf, the sheep, and the head of cabbage to the left side of the river in the man's boat.";
    }
}
