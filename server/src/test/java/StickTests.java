import game.Controller;
import game.Player;
import org.junit.Test;
import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;

/**
 * Created by fran on 09/06/16.
 */

@SuppressWarnings("CPD-START")
public class StickTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        driver.initGame(jarPath);
    }

    @Test
    public void stickQuest() {

        initGame(GamePaths.getGamePath("gameStick"));
        driver.sendCommand("pick stick");

        assert (Player.GameState.Win == driver.getGameState());
    }

}
