import server.driver.Driver;
import server.driver.GameDriver;

/**
 * Created by fran on 09/06/16.
 */

@SuppressWarnings("CPD-START")
public class EscapeTwoTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        driver.initGame(jarPath);
    }

}
