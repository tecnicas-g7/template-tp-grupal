

import exceptions.MaxInventoryException;
import game.Location;
import game.Player;
import game.actions.EnterAction;
import game.actions.MoveItemAction;

import game.actions.OpenCloseContainerAction;
import game.conditions.ComplexCondition;
import game.conditions.HasItemsWithItemsCondition;
import game.conditions.InventoryCondition;
import game.conditions.RoomCondition;
import game.items.Actionable;
import game.items.Container;
import game.tasks.ScheduledTask;
import model.Game;
import model.GameBuilder;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by fran on 02/06/16.
 */


@SuppressWarnings("CPD-START")
public class Escape implements GameBuilder {
    @Override
    public Game build() {
        try {
            Location pasillo = new Location("Pasillo");
            Player player = createPlayer(pasillo);
            setPlayerInitialInventory(player);
            Location acceso = new Location("BibliotecaAcceso");
            Location salonTres = new Location("Salon3");
            Location pasaje = new Location("Pasaje");
            Location biblioteca = createBiblioteca(player, acceso, pasaje);
            Location salon1 = createSalonUno(player, pasillo, acceso, biblioteca, salonTres);
            Location salon2 = createSalonDos(player,pasillo);



            Location afuera = new Location("Afuera");

            Location sotano = createSotano(afuera,salon2.getItem("Martillo"));

            pasaje.addDoor(sotano, null, "puerta", new EnterAction("enter"));

            Game game =  createGame(player, pasillo,salon1,salon2,salonTres,acceso,biblioteca,sotano,afuera);
            RoomCondition roomCondition = new RoomCondition(afuera,true);
            game.addLoseCondition(roomCondition);
            addPathsPasaje(game,pasaje,sotano,salon2.getItem("Martillo"));

            return game;
        } catch (MaxInventoryException e) {
            //kkk

        }
        return null;
    }

    @Override
    public String getName() {
        return "escape";
    }

    private void addPathsPasaje(Game game, Location pasaje, Location sotano, Actionable martillo) {
        Location escaleras = new Location("Escalera");
        pasaje.addDoor(escaleras,null,"Escalera",new EnterAction("use"));
        pasaje.addDoor(sotano, null, "Baranda", new EnterAction("use"));
        game.addLoseCondition(new RoomCondition(escaleras, true));

        RoomCondition roomCondition = new RoomCondition(sotano,true);
        List<Actionable> list = new ArrayList<>();
        list.add(martillo);
        InventoryCondition inventoryCondition = new InventoryCondition(list,false);
        ComplexCondition complexCondition = new ComplexCondition();
        complexCondition.addCondition(roomCondition);
        complexCondition.addCondition(inventoryCondition);
        game.addLoseCondition(complexCondition);
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
        game.makeLocationsAdjacent(salon1, pasillo, null,"goto");
        game.makeLocationsAdjacent(acceso, pasillo, null,"goto");
        game.makeLocationsAdjacent(acceso, biblioteca, null,"goto");
        game.makeLocationsAdjacent(salonTres,pasillo,null,"goto");
        game.makeLocationsAdjacent(salon2, pasillo, null,"goto");
        RoomCondition roomCondition = new RoomCondition(afuera,true);
        game.addLoseCondition(roomCondition);
        game.addTask(createScheduledTask(game, acceso.getItem("Bibliotecario")),300000,300000);
        return game;
    }

    private void setPlayerInitialInventory(Player player) throws MaxInventoryException {
        player.setMaxInventory(4);
        Actionable lapicera = new Actionable("lapicera");
        Actionable foto = new Actionable("Foto");
        player.addItem(foto);
        player.addItem(lapicera);
    }

    private Location createSotano(Location afuera, Actionable martillo) {
        Location sotano = new Location("Sotano");
        sotano.addDoor(afuera, martillo, "Ventana", new EnterAction("break"));
        return sotano;
    }

    private Location createPasaje(Location biblioteca) {
        Location pasaje = new Location("Pasaje");
        biblioteca.addDoor(pasaje,null,"LibroViejo",new EnterAction("move"));
        return pasaje;
    }

    private Location createBiblioteca(Player player, Location acceso, Location pasaje) {
        Location biblioteca = new Location("Biblioteca");
        Container estante = new Container("Estante",10);
        //estante.openContainer(player);
        estante.openContainer();
        biblioteca.addItem(estante);
        addBooks(estante);
        Actionable libroViejo = new Actionable("LibroViejo");
        estante.addComponent(libroViejo);
        List<Actionable> list = new ArrayList<>();
        list.add(libroViejo);
        pasaje.addEnterCondition(new HasItemsWithItemsCondition(list, estante, false));
        libroViejo.addAction(new MoveItemAction(false,true, "move"));
        biblioteca.addDoor(pasaje, null, "Sotano", new EnterAction("goto"));
        pasaje.addDoor(biblioteca,null,"Biblioteca",new EnterAction("goto"));
        return biblioteca;
    }

    private void addBooks(Container estante) {
        for (int i = 0; i < 9 ; i++) {
            int bookNumber = i + 1;
            String bookName = "book";
            bookName = bookName.concat(String.valueOf(bookNumber));
            Actionable libro = new Actionable(bookName);
            estante.addComponent(libro);
        }
    }

    private Location createSalonUno(Player player, Location pasillo, Location acceso, Location biblioteca, Location salonTres) {
        Location salonUno = new Location("Salon1");
        salonUno.addItem(new Actionable("mesa"));
        salonUno.addItem(new Actionable("vaso1"));
        salonUno.addItem(new Actionable("vaso2"));
        salonUno.addItem(new Actionable("silla1"));
        salonUno.addItem(new Actionable("silla2"));
        salonUno.addItem(new Actionable("cuadroTren"));
        createUsableItems(player, salonUno, acceso, biblioteca, salonTres);


        return salonUno;
    }

    private Location createSalonDos(Player player, Location pasillo) {
        Location salon2 = new Location("Salon2");
        Actionable martillo = new Actionable("Martillo");
        addPickDrop(martillo,player);
        salon2.addItem(martillo);
        salon2.addItem(new Actionable("Destornillador1"));
        salon2.addItem(new Actionable("Destornillador2"));
        return salon2;
    }

    private Container createCuadroBarco(Location salonUno) {
        Container cuadroBarco = new Container("CuadroBarco",1);
        //cuadroBarco.addAction(new OpenAction("move"));
        cuadroBarco.addAction(new OpenCloseContainerAction("move",Container.openStatus));
        salonUno.addItem(cuadroBarco);
        return cuadroBarco;
    }

    private void createUsableItems(Player player, Location salonUno, Location acceso, Location biblioteca, Location salonTres) {
        Container credencial = new Container("Credencial",1);
        Actionable key = new Actionable("Llave");
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
        //OpenAction open = new OpenAction("open");
        OpenCloseContainerAction open = new OpenCloseContainerAction("open",Container.openStatus);
        List<Actionable> list = new ArrayList<>();
        list.add(key);
        open.addCondition(new InventoryCondition(list, true));
        cajaFuerte.addAction(open);
        addPickDrop(credencial, player);
        Actionable licor = new Actionable( "licor");
        salonUno.addItem( licor);

        createLicorCredentialConditions(player, acceso, credencial, player.getItem("Foto"), licor, biblioteca);
    }

    private void createLicorCredentialConditions(Player player, Location acceso,
                                                 Container credencial, Actionable foto, Actionable licor, Location biblioteca) {
        foto.addAction(new MoveItemAction(true, false, "put"));
        addPickDrop(licor, player);
        addPickDrop(foto, player);
        List<Actionable> list = new ArrayList<>();
        list.add(foto);
        MoveItemAction show = new MoveItemAction(true,false,"show");
        show.addCondition(new HasItemsWithItemsCondition(list, credencial, true));
        credencial.addAction(show);
        createAccesoBiblioteca(foto, credencial, acceso, biblioteca);

    }

    private void createAccesoBiblioteca(Actionable foto, Container credencial, Location acceso, Location biblioteca) {
        //TODO FALTA LO DEL bibliotecario con licor y q se acuerde de no dejar pasar
        Container bibliotecario = new Container("Bibliotecario",1);
        acceso.addItem(bibliotecario);
        List<Actionable> listCredencial = new ArrayList<>();
        listCredencial.add(credencial);
        biblioteca.addEnterCondition(new HasItemsWithItemsCondition(listCredencial, bibliotecario, true));
        List<Actionable> listCredencialConFoto = new ArrayList<>();
        listCredencialConFoto.add(foto);
        biblioteca.addEnterCondition(new HasItemsWithItemsCondition(listCredencialConFoto, credencial, true));
    }

    private void addPickDrop(Actionable actionable, Player player) {
        actionable.addAction(new MoveItemAction(false,true,"pick"));
        actionable.addAction(new MoveItemAction(true,false,"drop"));
    }

    private ScheduledTask createScheduledTask(Game game, Actionable item) {
        return new ScheduledTask(game) {
            @Override
            public void run() {
                try {
                    Location location = game.findItemLocation(item);
                    location.removeItem(item.getName());
                    Location newLocation = location.getRandomAdjacentLocation();
                    newLocation.addItem(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @SuppressWarnings("CPD-END")

    @Override
    public String getHelp() {
        return null;
    }
}