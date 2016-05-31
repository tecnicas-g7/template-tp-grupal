package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.MoveItemAction;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.tasks.ScheduledTask;

/*
Created by fran on 24/04/16.
*/

@SuppressWarnings("CPD-START")
public class EnterRoom implements GameFactory {

    public Game getGame() {
        Item key = new Item("key");


        Location room1 = new Location("Room1");
        room1.addItem(key);
        Location room2 = new Location("Room2");


        Player player = createPlayer(room1);
        key.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);
        game.makeLocationsAdjacent(room1, room2, key);
        game.addRoom(room1);
        game.addRoom(room2);
        game.addTask(createScheduledTask(game),50000,150000);

        game.addCondition(new RoomCondition(room2,true));

        return game;
    }

    private ScheduledTask createScheduledTask(Game game) {
        return new ScheduledTask() {
            @Override
            public void run() {
                try {
                    game.getPlayer().changeStatus(Player.Status.dead);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @SuppressWarnings("CPD-END")

    public String getHelp() {
        return "The player must find the way out of the room.";
    }
}
