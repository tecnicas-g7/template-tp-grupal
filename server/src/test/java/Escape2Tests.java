import org.junit.Test;
import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;

import static org.junit.Assert.assertTrue;

/**Created by javier on 6/9/16.
 */
public class Escape2Tests {

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
        try {
            //TODO Se cambisron valores en escape2 por un tema de acelerar los tests. Volver atras
            Thread.sleep(20000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        assertTrue(driver.getItemStatus("Bibliotecario").equalState("angry"));

    }


}
