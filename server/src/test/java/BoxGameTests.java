import game.Controller;
import org.junit.Test;
import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;

/**
 * Created by fran on 09/06/16.
 */

@SuppressWarnings("CPD-START")
public class BoxGameTests {


    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        driver.initGame(jarPath);
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
