package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.ListInventoryAction;
import ar.fiuba.tdd.tp.game.actions.LookAction;
import ar.fiuba.tdd.tp.game.actions.MoveItemAction;
import ar.fiuba.tdd.tp.game.conditions.Condition;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.PlayerStateCondition;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.states.StatePlayer;
import ar.fiuba.tdd.tp.tasks.ScheduledTask;

import java.util.ArrayList;
import java.util.List;

/*
Created by fran on 24/04/16.
*/

public class StickGame implements GameFactory {

    public Game getGame() {
        Location room = new Location("room");

        Actionable stick = new Actionable("stick");

        room.addItem(stick);

        Player player = new Player(room);
        player.addAction(new LookAction("look"));
        player.addAction(new ListInventoryAction("inventory"));
        stick.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);

        game.addRoom(room);

        List<Actionable> items = new ArrayList<>();
        items.add(stick);

        Condition condition = new InventoryCondition(items, true);
        game.addCondition(condition);
        game.addLoseCondition(new PlayerStateCondition(new StatePlayer("dead")));
        game.addTask(createScheduledTask(game),15000,150000);

        return game;
    }

    private ScheduledTask createScheduledTask(Game game) {
        return new ScheduledTask() {
            @Override
            public void run() {
                try {
                    game.getPlayer().changeStatus(new StatePlayer("dead"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public String getHelp() {
        return "The player will look for an item and find it to win";
    }


}
