import game.Controller;
import org.junit.Test;
import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;

/**
 * Created by fran on 09/06/16.
 */

@SuppressWarnings("CPD-START")
public class TreasureTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        driver.initGame(jarPath);
    }

    @Test
    public void treasure() {

        initGame(GamePaths.getGamePath("gameTreasureBox"));

        String command = "open box";
        driver.sendCommand(command);
        String command2 = "pick key";
        driver.sendCommand(command2);
        String command3 = "enter door1";
        driver.sendCommand(command3);
        String command4 = "enter door2";
        driver.sendCommand(command4);
        String command5 = "open trunk";
        driver.sendCommand(command5);
        String command6 = "drop key";
        driver.sendCommand(command6);
        String command7 = "pick antidote";
        driver.sendCommand(command7);
        interpretTreasureCommands();

        assert(Controller.GameState.Win == driver.getGameState());

    }

    private void interpretTreasureCommands() {

        String command = "open box";
        driver.sendCommand(command);
        command = "pick key2";
        driver.sendCommand(command);
        command = "drink antidote";
        driver.sendCommand(command);
        command = "drop antidote";
        driver.sendCommand(command);
        command = "enter door1";
        driver.sendCommand(command);
        command = "enter door3";
        driver.sendCommand(command);
        command = "enter door2";
        driver.sendCommand(command);
        command = "pick treasure";
        driver.sendCommand(command);
    }
}
