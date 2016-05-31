package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class StickGame {
    @Test
    public void stickQuest() {

        Game stickGame = (new ar.fiuba.tdd.tp.game.types.StickGame()).getGame();
        Controller controller = new Controller(stickGame);

        String command = "pick stick";
        controller.interpretCommand(command);

        assertTrue(stickGame.verifyVictory());
    }
}
