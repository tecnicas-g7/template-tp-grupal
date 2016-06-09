import game.Controller;
import org.junit.Test;
import server.GamePaths;
import server.driver.Driver;
import server.driver.GameDriver;

/**
 * Created by fran on 09/06/16.
 */

@SuppressWarnings("CPD-START")
public class EscapeTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        driver.initGame(jarPath);
    }

    public void credencialConFoto() {
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto Credencial");
        driver.sendCommand("goto Pasillo");
    }

    public void bibliotecaToSotano() {
        credencialConFoto();
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");

    }


    @Test
    public void escapeStairs() {

        initGame(GamePaths.getGamePath("gameEscape"));
        bibliotecaToSotano();
        driver.sendCommand("use Escalera");
        assert(Controller.GameState.Lost == driver.getGameState());

    }

    @Test
    public void escapeBaranda() {

        initGame(GamePaths.getGamePath("gameEscape"));
        bibliotecaToSotano();
        driver.sendCommand("use Baranda");
        assert(Controller.GameState.Lost == driver.getGameState());

    }

    @Test
    public void escapeYouWin() {

        initGame(GamePaths.getGamePath("gameEscape"));

        credencialConFoto();
        driver.sendCommand("goto Salon2");
        driver.sendCommand("pick Martillo");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Baranda");
        driver.sendCommand("break Ventana");
        driver.sendCommand("goto Afuera");
        assert(Controller.GameState.Win == driver.getGameState());

    }

}
