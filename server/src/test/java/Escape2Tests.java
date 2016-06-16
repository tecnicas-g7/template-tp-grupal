import org.junit.Test;
import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;

import static org.junit.Assert.assertTrue;

/*Created by javier on 6/9/16.
 */
@SuppressWarnings("CPD-START")
public class Escape2Tests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        System.out.println("Path: " + jarPath);
        driver.initGame(jarPath);
    }

    @Test
    public void getBibliotecarioDrunk() {

        initGame(GamePaths.getGamePath("gameEscape2"));

        driver.sendCommand("goto Salon1");
        driver.sendCommand("pick licor");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("give licor Bibliotecario");
        assertTrue(driver.getItemStatus("Bibliotecario").equalState("asleep"));

    }

    @Test
    public void bibliotecarioAwakes() {

        initGame(GamePaths.getGamePath("gameEscape2"));


        driver.sendCommand("goto Salon1");
        driver.sendCommand("pick licor");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("give licor Bibliotecario");
        driver.simulatePassingOfTime(121);
        assertTrue(driver.getItemStatus("Bibliotecario").equalState("angry"));

    }


}
