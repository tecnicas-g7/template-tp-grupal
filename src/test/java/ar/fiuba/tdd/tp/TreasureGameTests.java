package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.TreasureGame;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class TreasureGameTests {

    @Test
    public void treasure() {
        Game treasureGame = (new TreasureGame()).getGame();
        Controller controller = new Controller(treasureGame);

        String command0 = "look";
        System.out.println(controller.interpretCommand(command0));
        String command = "open box";
        System.out.println(controller.interpretCommand(command));
        String command2 = "pick key";
        System.out.println(controller.interpretCommand(command2));
        String command3 = "enter door1";
        System.out.println(controller.interpretCommand(command3));
        String command4 = "enter door2";
        System.out.println(controller.interpretCommand(command4));
        String command5 = "open trunk";
        System.out.println(controller.interpretCommand(command5));
        String command6 = "drop key";
        controller.interpretCommand(command6);

        interpretTreasureCommands(controller);

        assertTrue(treasureGame.verifyVictory());

    }

    private void interpretTreasureCommands(Controller controller) {
        String command7 = "pick antidote";
        controller.interpretCommand(command7);
        String command = "open box";
        controller.interpretCommand(command);
        command = "pick key2";
        controller.interpretCommand(command);
        command = "drink antidote";
        controller.interpretCommand(command);
        command = "enter door1";
        controller.interpretCommand(command);
        command = "enter door3";
        controller.interpretCommand(command);
        command = "enter door2";
        controller.interpretCommand(command);
        command = "pick treasure";
        controller.interpretCommand(command);
    }
}
