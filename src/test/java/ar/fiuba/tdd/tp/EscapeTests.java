package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.Escape;
import org.junit.Assert;
import org.junit.Test;


public class EscapeTests {
    @Test
    public void escapeStairs() {
        Game escape = (new Escape().getGame());
        Controller controller = new Controller(escape);

        controller.interpretCommand("goto BibliotecaAcceso");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto Salon3");
        controller.interpretCommand("pick Llave");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto Salon1");
        controller.interpretCommand("move CuadroBarco");
        controller.interpretCommand("open CajaFuerte");
        controller.interpretCommand("pick Credencial");
        controller.interpretCommand("put Foto Credencial");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto BibliotecaAcceso");
        controller.interpretCommand("show Credencial Bibliotecario");
        controller.interpretCommand("goto Biblioteca");
        controller.interpretCommand("move LibroViejo");
        controller.interpretCommand("goto Sotano");
        controller.interpretCommand("use Escalera");
        Assert.assertTrue(controller.gameOver());

    }

    @Test
    public void escapeBaranda() {
        Game escape = (new Escape().getGame());
        Controller controller = new Controller(escape);

        controller.interpretCommand("goto BibliotecaAcceso");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto Salon3");
        controller.interpretCommand("pick Llave");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto Salon1");
        controller.interpretCommand("move CuadroBarco");
        controller.interpretCommand("open CajaFuerte");
        controller.interpretCommand("pick Credencial");
        controller.interpretCommand("put Foto Credencial");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto BibliotecaAcceso");
        controller.interpretCommand("show Credencial Bibliotecario");
        controller.interpretCommand("goto Biblioteca");
        controller.interpretCommand("move LibroViejo");
        controller.interpretCommand("goto Sotano");
        controller.interpretCommand("use Baranda");
        Assert.assertTrue(controller.gameOver());

    }

    @Test
    public void escapeYouWin() {
        Game escape = (new Escape().getGame());
        Controller controller = new Controller(escape);

        controller.interpretCommand("goto BibliotecaAcceso");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto Salon3");
        controller.interpretCommand("pick Llave");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto Salon1");
        controller.interpretCommand("move CuadroBarco");
        controller.interpretCommand("open CajaFuerte");
        controller.interpretCommand("pick Credencial");
        controller.interpretCommand("put Foto Credencial");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto Salon2");
        controller.interpretCommand("pick Martillo");
        controller.interpretCommand("goto Pasillo");
        controller.interpretCommand("goto BibliotecaAcceso");
        controller.interpretCommand("show Credencial Bibliotecario");
        controller.interpretCommand("goto Biblioteca");
        controller.interpretCommand("move LibroViejo");
        controller.interpretCommand("goto Sotano");
        controller.interpretCommand("use Baranda");
        controller.interpretCommand("break Ventana");
        controller.interpretCommand("goto Afuera");
        Assert.assertTrue(controller.verify());

    }
}
