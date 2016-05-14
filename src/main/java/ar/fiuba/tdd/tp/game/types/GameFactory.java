package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Game;

/**
 * Created by javier on 4/28/16.
 */
public interface GameFactory {

    public Game getGame();

    public String getHelp();

}
