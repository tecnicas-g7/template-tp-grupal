package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.CursedItem;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class CursedItemTests {

    @Test
    public void cursedItem() {

        Game cursedItem = (new CursedItem()).getGame();
        Controller controller = new Controller(cursedItem);

        String command = "pick cursed_item";
        controller.interpretCommand(command);
        String command2 = "enter door1";
        controller.interpretCommand(command2);
        String command3 = "talk thief";
        controller.interpretCommand(command3);
        String command4 = "enter door1";
        controller.interpretCommand(command4);

        assertTrue(cursedItem.verifyVictory());
    }
}
