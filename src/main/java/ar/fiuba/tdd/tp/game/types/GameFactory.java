package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Game;

public interface GameFactory {

    Game getGame();

    String getHelp();

}
