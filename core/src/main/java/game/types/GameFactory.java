package game.types;

import game.Game;

public interface GameFactory {

    Game getGame();

    String getHelp();

}
