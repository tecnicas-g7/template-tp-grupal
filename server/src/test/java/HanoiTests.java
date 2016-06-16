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
public class HanoiTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        driver.initGame(jarPath);
    }

    @Test
    public void hanoiTower() {

        initGame(GamePaths.getGamePath("gameHanoiTower"));
        String command = "move stack1 stack3";
        driver.sendCommand(command);
        String command2 = "move stack1 stack2";
        driver.sendCommand(command2);
        String command3 = "move stack3 stack2";
        driver.sendCommand(command3);
        String command4 = "move stack1 stack3";
        driver.sendCommand(command4);
        String command5 = "move stack2 stack1";
        driver.sendCommand(command5);
        String command6 = "move stack2 stack3";
        driver.sendCommand(command6);
        String command7 = "move stack1 stack3";
        driver.sendCommand(command7);

        assert(Player.GameState.Win == driver.getGameState());

    }
}
