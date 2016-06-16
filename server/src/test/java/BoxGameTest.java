import game.Controller;
import org.junit.Test;

import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;



@SuppressWarnings("CPD-START")
public class BoxGameTest {

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