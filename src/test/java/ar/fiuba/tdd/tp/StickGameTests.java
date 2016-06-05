package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.EnterRoom;
import ar.fiuba.tdd.tp.game.types.StickGame;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class StickGameTests {
    @Test
    public void stickQuest() {

        Game stickGame = (new StickGame()).getGame();
        Controller controller = new Controller(stickGame);

        String command = "pick stick";
        controller.interpretCommand(command);

        assertTrue(stickGame.verifyVictory());
    }


    @Test
    public void gameOver() {

        Game stickGame = (new StickGame()).getGame();
        try {
            Thread.sleep(16000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        assertTrue(stickGame.gameOver());
    }

    @Test
    public void gameNotOver() {

        Game stickGame = (new StickGame()).getGame();
        try {
            Thread.sleep(14000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        assertFalse(stickGame.gameOver());
    }
}
