package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.HanoiTower;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class HanoiTowerTests {

    @Test
    public void hanoiTower() {

        Game hanoiTower = ( new HanoiTower()).getGame();
        Controller controller = new Controller(hanoiTower);
        String command = "move stack1 stack3";
        controller.interpretCommand(command);
        String command2 = "move stack1 stack2";
        controller.interpretCommand(command2);
        String command3 = "move stack3 stack2";
        controller.interpretCommand(command3);
        String command4 = "move stack1 stack3";
        controller.interpretCommand(command4);
        String command5 = "move stack2 stack1";
        controller.interpretCommand(command5);
        String command6 = "move stack2 stack3";
        controller.interpretCommand(command6);
        String command7 = "move stack1 stack3";
        controller.interpretCommand(command7);

        assertTrue(hanoiTower.verifyVictory());

    }
}
