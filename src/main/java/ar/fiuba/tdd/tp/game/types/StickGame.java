package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.MoveItemAction;
import ar.fiuba.tdd.tp.game.conditions.Condition;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.items.Describable;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.ArrayList;
import java.util.List;

/*
Created by fran on 24/04/16.
*/

public class StickGame implements GameFactory {

    public Game getGame() {
        Location room = new Location("room");

        Item stick = new Item("stick");

        room.addItem(stick);

        Player player = new Player(room);
        stick.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);

        game.addRoom(room);

        List<Describable> items = new ArrayList<>();
        items.add(stick);

        Condition condition = new InventoryCondition(items, true);
        game.addCondition(condition);

        return game;
    }

    public String getHelp() {
        return "The player will look for an item and find it to win";
    }


}
