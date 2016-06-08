import game.Controller;
import org.junit.Test;
import server.Driver;
import server.GameDriver;
import server.GamePaths;

import static org.junit.Assert.assertTrue;


public class BoxGameTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        try {
            driver.initGame(jarPath);
        } catch (Exception e) {
            //
        }
    }

    @Test
    public void boxGame() {
        initGame(GamePaths.getGamePath("gameBox"));
        String command = "open box";
        driver.sendCommand(command);
        String command2 = "pick key";
        driver.sendCommand(command2);
        String command3 = "enter door1";
        driver.sendCommand(command3);

        assert(Controller.GameState.Win == driver.getGameState());
    }
}