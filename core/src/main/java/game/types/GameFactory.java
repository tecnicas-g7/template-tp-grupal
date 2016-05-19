package game.types;

import model.Game;

public interface GameFactory {

    Game getGame();

    String getHelp();
}
