import game.Controller;
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
        driver.simulatePassingOfTime(60);
        driver.simulatePassingOfTime(61);
        assertTrue(driver.getItemStatus("Bibliotecario").equalState("angry"));

    }

    //Tests entrega3

    @Test
    public void primerCaso() {

        initGame(GamePaths.getGamePath("gameEscape2"));
        driver.sendCommand("goto Salon1", "Player 1");
        driver.sendCommand("pick licor", "Player 1");
        driver.sendCommand("goto Pasillo","Player 1");
        driver.sendCommand("goto BibliotecaAcceso","Player 1");
        driver.sendCommand("give licor Bibliotecario","Player 1");
        driver.sendCommand("goto Biblioteca","Player 1");
        driver.sendCommand("goto BibliotecaAcceso", "Player 1");
        driver.simulatePassingOfTime(121);
        assert(driver.getGameState("Player 1") == Controller.GameState.Lost);
    }

    @Test
    public void segundoCaso() {
        initGame(GamePaths.getGamePath("gameEscape2"));
        driver.sendCommand("goto Salon1", "Player 1");
        driver.sendCommand("pick licor", "Player 1");
        driver.sendCommand("goto Pasillo","Player 1");
        driver.sendCommand("goto BibliotecaAcceso","Player 1");
        driver.sendCommand("give licor Bibliotecario","Player 1");
        driver.sendCommand("goto Biblioteca","Player 1");
        driver.sendCommand("goto BibliotecaAcceso","Player 2");
        driver.sendCommand("goto Biblioteca","Player 2");
        driver.sendCommand("goto BibliotecaAcceso","Player 2");
        driver.sendCommand("goto Pasillo","Player 2");


    }



}
