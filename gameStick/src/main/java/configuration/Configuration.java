package configuration;

import game.Location;
import game.Player;
import game.actions.MoveItemAction;
import game.conditions.Condition;
import game.conditions.InventoryCondition;
import game.items.Actionable;
import game.items.Item;
import model.Game;
import model.GameBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicol on 18/5/2016.
 */
public class Configuration implements GameBuilder {

    public Game build() {

        Location room = new Location("room");

        Item stick = new Item("stick");

        room.addItem(stick);

        Player player = new Player(room);
        stick.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);

        game.addRoom(room);

        List<Actionable> items = new ArrayList<Actionable>();
        items.add(stick);

        Condition condition = new InventoryCondition(items, true);
        game.addCondition(condition);

        return game;
    }

    @Override
    public String getName() {
        return "gameStick";
    }

    public String getHelp() {
        return "The player will look for an item and find it to win";
    }

}
