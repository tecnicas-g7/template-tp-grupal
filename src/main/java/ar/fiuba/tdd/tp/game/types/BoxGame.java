package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.actions.*;
import ar.fiuba.tdd.tp.game.conditions.PlayerStateCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.items.Container;
import ar.fiuba.tdd.tp.game.states.StatePlayer;
import ar.fiuba.tdd.tp.tasks.DeadLine;

/**
 Created by fran on 27/04/16.
 */

@SuppressWarnings("CPD-START")
public class BoxGame implements GameFactory {

    public Game getGame() {

        Container box = new Container("box",1);
        box.addAction(new OpenAction("open"));
        box.addAction(new CloseAction("close"));

        Location room1 = new Location("Room1");
        room1.addItem(box);
        Actionable key = new Actionable("key");


        box.addComponent(key);
        Location room2 = new Location("Room2");

        Player player = createPlayer(room1);
        key.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);
        game.makeLocationsAdjacent(room1, room2, key);
        game.addCondition(new RoomCondition(room2,true));

        game.addRoom(room1);
        game.addRoom(room2);
        game.addLoseCondition(new PlayerStateCondition(new StatePlayer("dead")));
        game.addTask(new DeadLine(game),45000,150000);

        return game;
    }


    @SuppressWarnings("CPD-END")
    public String getHelp() {
        return "To leave the room the player must find the hidden key";
    }

}
