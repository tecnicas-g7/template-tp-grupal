package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.ListInventoryAction;
import ar.fiuba.tdd.tp.game.actions.LookAction;

public interface GameFactory {

    Game getGame();

    String getHelp();

    default Player createPlayer(Location room1) {
        Player player = new Player(room1);
        player.addAction(new LookAction("look"));
        player.addAction(new ListInventoryAction("inventory"));
        return player;
    }
}
