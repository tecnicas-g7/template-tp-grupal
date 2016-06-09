import game.Controller;
import org.junit.Test;
import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;

/**
 * Created by fran on 09/06/16.
 */

@SuppressWarnings("CPD-START")
public class RiverTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        driver.initGame(jarPath);
    }

    private void simpleCross( String animal, String moveTo) {
        driver.sendCommand("take " + animal);
        driver.sendCommand("cross " + moveTo);
        driver.sendCommand("leave " + animal);
    }

    @Test
    public void riverCrossingVictory() {


        initGame(GamePaths.getGamePath("gameRiverCrossing"));

        simpleCross("sheep", "north-shore");

        driver.sendCommand("cross south-shore");

        //driver.sendCommand("cross south-shore");

        simpleCross("wolf", "north-shore");

        driver.sendCommand("take sheep");
        driver.sendCommand("cross south-shore");
        driver.sendCommand("leave sheep");


        simpleCross("cabbage", "north-shore");

        driver.sendCommand("cross south-shore");

        //driver.sendCommand("cross south-shore");

        simpleCross("sheep", "north-shore");


        assert(Controller.GameState.Win == driver.getGameState());

    }

    @Test
    public void riverCrossingFail() {


        initGame(GamePaths.getGamePath("gameRiverCrossing"));
        String takeSheep = "take sheep";
        String leaveSheep = "leave sheep";

        String takeWolf = "take wolf";
        String leaveWolf = "leave wolf";

        String northShore = "cross north-shore";
        String crossSouth = "cross south-shore";

        driver.sendCommand(takeSheep);
        driver.sendCommand(northShore);
        driver.sendCommand(leaveSheep);

        driver.sendCommand(crossSouth);

        driver.sendCommand(takeWolf);
        driver.sendCommand(northShore);
        driver.sendCommand(leaveWolf);


        assert(Controller.GameState.Win != driver.getGameState());
    }
}
