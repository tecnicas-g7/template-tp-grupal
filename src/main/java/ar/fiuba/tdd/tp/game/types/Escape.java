package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.EnterAction;
import ar.fiuba.tdd.tp.game.actions.MoveItemAction;
import ar.fiuba.tdd.tp.game.actions.OpenAction;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.items.Container;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CPD-START")
public class Escape implements GameFactory {
    @Override
    public Game getGame() {
        try {
            Location pasillo = new Location("Pasillo");
            Player player = createPlayer(pasillo);
            setPlayerInitialInventory(player);
            Location acceso = new Location("BibliotecaAcceso");
            Location salonTres = new Location("Salon3");
            Location salon1 = createSalonUno(player,pasillo,acceso,salonTres);
            makeLocationsAdjacent(salon1,pasillo,null);
            Location salon2 = createSalonDos(pasillo);
            createAccesoBiblioteca(pasillo, acceso);
            Location biblioteca = createBiblioteca(acceso);
            Location pasaje = createPasaje(biblioteca);
            Location afuera = new Location("Afuera");
            Location sotano = createSotano(afuera,salon2.getItem("Martillo"));
            pasaje.addDoor(sotano, null, "puerta", new EnterAction("enter"));

            return createGame(player, pasillo,salon1,salon2,salonTres,acceso,biblioteca,sotano,afuera);
        } catch (MaxInventoryException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Game createGame(Player player,Location pasillo, Location salon1,
                            Location salon2, Location salonTres, Location acceso, Location biblioteca, Location sotano, Location afuera) {
        Game game = new Game(player);
        game.addRoom(pasillo);
        game.addRoom(salon1);
        game.addRoom(salon2);
        game.addRoom(salonTres);
        game.addRoom(acceso);
        game.addRoom(biblioteca);
        game.addRoom(sotano);
        game.addRoom(afuera);
        game.addCondition(new RoomCondition(afuera,true));
        return game;
    }

    private void setPlayerInitialInventory(Player player) throws MaxInventoryException {
        player.setMaxInventory(4);
        Item lapicera = new Item("lapicera");
        Item foto = new Item("Foto");
        player.addItem(foto);
        player.addItem(lapicera);
    }

    private Location createSotano(Location afuera, Actionable martillo) {
        Location sotano = new Location("Sotano");
        sotano.addDoor(afuera,(Item)martillo,"ventana",new EnterAction("break"));
        return sotano;
    }

    private Location createPasaje(Location biblioteca) {
        Location pasaje = new Location("Pasaje");
        biblioteca.addDoor(pasaje,null,"LibroViejo",new EnterAction("move"));
        return pasaje;
    }

    private Location createBiblioteca(Location acceso) {
        Location biblioteca = new Location("Biblioteca");
        makeLocationsAdjacent(acceso, biblioteca,null);
        Item estante = new Item("Estante");
        biblioteca.addItem(estante);
        for (int i = 0; i < 9 ; i++) {
            int bookNumber = i + 1;
            String bookName = "book";
            bookName = bookName.concat(String.valueOf(bookNumber));
            Item libro = new Item(bookName);
            biblioteca.addItem(libro);
        }
        return biblioteca;
    }

    private void createAccesoBiblioteca(Location pasillo, Location acceso) {
        makeLocationsAdjacent(acceso, pasillo, null);
        //TODO FALTA LO DEL bibliotecario
    }

    private Location createSalonUno(Player player, Location pasillo, Location acceso, Location salonTres) {
        Location salonUno = new Location("Salon1");
        makeLocationsAdjacent(salonTres,pasillo,null);
        salonUno.addItem(new Item("mesa"));
        salonUno.addItem(new Item("vaso1"));
        salonUno.addItem(new Item("vaso2"));
        salonUno.addItem(new Item("silla1"));
        salonUno.addItem(new Item("silla2"));
        salonUno.addItem(new Item("cuadroTren"));
        createUsableItems(player,salonUno, acceso, salonTres);


        return salonUno;
    }

    private Location createSalonDos(Location pasillo) {
        Location salon2 = new Location("Salon2");
        makeLocationsAdjacent(salon2,pasillo,null);
        salon2.addItem(new Item("Martillo"));
        salon2.addItem(new Item("Destornillador1"));
        salon2.addItem(new Item("Destornillador2"));
        return salon2;
    }

    private Container createCuadroBarco(Location salonUno) {
        Container cuadroBarco = new Container("CuadroBarco",1);
        cuadroBarco.addAction(new OpenAction("move"));
        salonUno.addItem(cuadroBarco);
        return cuadroBarco;
    }

    private void createUsableItems(Player player,Location salonUno, Location acceso, Location salonTres) {
        Container credencial = new Container("Credencial",1);
        Item key = new Item("Llave");
        addPickDrop(key,player);
        salonTres.addItem(key);
        Container cajaFuerte = new Container("CajaFuerte",1);
        Container cuadroBarco = createCuadroBarco(salonUno);
        try {
            cuadroBarco.addComponent(cajaFuerte);
            cajaFuerte.addItem(credencial);
        } catch (MaxInventoryException e) {
            e.printStackTrace();
        }
        OpenAction open = new OpenAction("open");
        List<Actionable> list = new ArrayList<>();
        list.add(key);
        open.addCondition(new InventoryCondition(list, true));
        cajaFuerte.addAction(open);
        addPickDrop(credencial, player);
        Item licor = new Item("licor");
        salonUno.addItem(licor);

        createLicorCredentialConditions(player, credencial, player.getItem("Foto"), licor, acceso);
    }

    private void createLicorCredentialConditions(Player player, Container credencial, Actionable foto, Item licor, Location acceso) {
        foto.addAction(new MoveItemAction(player,null,"put"));
        addPickDrop(licor,player);
        addPickDrop(foto,player);
    }

    private void addPickDrop(Actionable actionable, Player player) {
        actionable.addAction(new MoveItemAction(null,player,"pick"));
        actionable.addAction(new MoveItemAction(player,null,"drop"));
    }

    @Override
    public void makeLocationsAdjacent(Location room1, Location room2, Item key) {
        EnterAction enterAction = new EnterAction("goto");
        room1.addDoor(room2,key,room2.getName(),enterAction);
        room2.addDoor(room1,key,room1.getName(),enterAction);
    }

    @SuppressWarnings("CPD-END")
    @Override
    public String getHelp() {
        return null;
    }
}
