import game.Controller;
import org.junit.Test;
import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;

/**
 * Created by fran on 09/06/16.
 */


@SuppressWarnings("CPD-START")
public class CursedItemTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        driver.initGame(jarPath);
    }


    @Test
    public void cursedItem() {

        initGame(GamePaths.getGamePath("gameCursedItem"));
        String command = "pick cursed_item";
        driver.sendCommand(command);
        String command2 = "enter door1";
        driver.sendCommand(command2);
        String command3 = "talk thief";
        driver.sendCommand(command3);
        String command4 = "enter door1";
        driver.sendCommand(command4);


        assert(Controller.GameState.Win == driver.getGameState());
    }
}
