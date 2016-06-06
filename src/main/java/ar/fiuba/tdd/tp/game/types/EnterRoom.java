package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.MoveItemAction;
import ar.fiuba.tdd.tp.game.conditions.PlayerStateCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.states.State;
import ar.fiuba.tdd.tp.game.states.StatePlayer;
import ar.fiuba.tdd.tp.tasks.DeadLine;
import ar.fiuba.tdd.tp.tasks.ScheduledTask;

/*
Created by fran on 24/04/16.
*/

@SuppressWarnings("CPD-START")
public class EnterRoom implements GameFactory {

    public Game getGame() {
        Actionable key = new Actionable("key");


        Location room1 = new Location("Room1");
        room1.addItem(key);
        Location room2 = new Location("Room2");


        Player player = createPlayer(room1);
        key.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);
        game.makeLocationsAdjacent(room1, room2, key);
        game.addRoom(room1);
        game.addRoom(room2);
        game.addTask(new DeadLine(game),15000,150000);
        game.addLoseCondition(new PlayerStateCondition(new StatePlayer("dead")));

        game.addCondition(new RoomCondition(room2,true));

        return game;
    }

    @SuppressWarnings("CPD-END")

    public String getHelp() {
        return "The player must find the way out of the room.";
    }
}
