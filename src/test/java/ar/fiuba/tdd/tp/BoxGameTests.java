package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.BoxGame;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class BoxGameTests {

    @Test
    public void boxGame() {

        Game boxGame = (new BoxGame()).getGame();
        Controller controller = new Controller(boxGame);

        String command = "open box";
        controller.interpretCommand(command);
        String command2 = "pick key";
        controller.interpretCommand(command2);
        String command3 = "enter door1";
        controller.interpretCommand(command3);

        assertTrue(boxGame.verifyVictory());
    }
}
