
import game.Player;
import org.junit.Test;
import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;

/**
 * Created by fran on 09/06/16.
 */

@SuppressWarnings("CPD-START")
public class EnterRoomTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        driver.initGame(jarPath);
    }

    @Test
    public void enterRoom() {

        initGame(GamePaths.getGamePath("gameEnterRoom"));
        driver.sendCommand("pick key");
        driver.sendCommand("enter door1");

        assert(Player.GameState.Win == driver.getGameState());
    }


}
