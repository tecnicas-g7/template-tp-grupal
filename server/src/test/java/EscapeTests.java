
import game.Controller;

import server.GameDriver;
import server.Driver;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import org.junit.Before;

public class EscapeTests {

    private GameDriver driver;

    @Before
    public void initGame() {
        driver = new Driver();
        try {
            driver.initGame("/home/fran/git/template-tp-grupal/gameStick/build/libs/gameStick-1.0.jar");
        } catch (Exception e) {
            //
        }
    }
/*
    @Test
    public void testDieStairs() {
        driver.sendCommand("pick stick");
        assert(Controller.GameState.Win == driver.getGameState());
    }
*/
}