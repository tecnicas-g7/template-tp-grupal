package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.EnterRoom;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class EnterRoomTests {
    @Test
    public void enterRoom() {

        Game enterRoom = (new EnterRoom()).getGame();
        Controller controller = new Controller(enterRoom);

        String command = "pick key";
        controller.interpretCommand(command);
        String command2 = "enter door1";
        controller.interpretCommand(command2);

        assertTrue(enterRoom.verifyVictory());
    }
}
