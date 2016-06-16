import exceptions.MaxInventoryException;
import game.Location;
import game.Player;
import game.actions.*;

import game.conditions.*;
import game.items.Actionable;
import game.items.Container;
import game.states.Status;
import game.tasks.ScheduledTask;
import model.Game;
import model.GameBuilder;


import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("CPD-START")
public class Escape2 implements GameBuilder {
    private Game game;
    @Override
    public Game build() {
        try {
            Location pasillo = new Location("Pasillo");
            Player player = createPlayer(pasillo, "Player 1");
            setPlayerInitialInventory(player,1);
            game = new Game(player);
            Player player2 = createPlayer(pasillo, "Player 2");
            setPlayerInitialInventory(player2, 2);
            Player player3 = createPlayer(pasillo, "Player 3");
            setPlayerInitialInventory(player3, 3);
            Player player4 = createPlayer(pasillo, "Player 4");
            setPlayerInitialInventory(player4, 4);

            game.addPlayer(player2);
            game.addPlayer(player3);
            game.addPlayer(player4);

            Location acceso = new Location("BibliotecaAcceso");
            Location salonTres = new Location("Salon3");
            Location pasaje = new Location("Pasaje");
            //Location biblioteca = createBiblioteca(player, acceso, pasaje);
            //Location salon1 = createSalonUno(player, pasillo, acceso, biblioteca, salonTres);
            //Location salon2 = createSalonDos(player,pasillo);
            Location biblioteca = new Location("Biblioteca");
            Location salon1 = new Location("Salon1");
            Location salon2 = new Location("Salon2");
            Location afuera = new Location("Afuera");
            Location sotano = new Location("Sotano");
            createGame(player, pasillo, salon1, salon2, salonTres, acceso, biblioteca, sotano, afuera);


            createBiblioteca(biblioteca, player, acceso, pasaje);
            createSalonUno(salon1, player, pasillo, acceso, biblioteca, salonTres);
            createSalonDos(salon2, player, pasillo);




            //Location sotano = createSotano(afuera,salon2.getItem("Martillo"));

            createSotano(sotano, afuera, salon2.getItem("Martillo"));
            pasaje.addDoor(sotano, null, "puerta", new EnterAction("enter"));


            addPathsPasaje(pasaje, sotano, salon2.getItem("Martillo"));

            return game;
        } catch (MaxInventoryException e) {
            //kkk

        }
        return null;
    }

    @Override
    public String getName() {
        return "escape2";
    }

    private void addPathsPasaje(Location pasaje, Location sotano, Actionable martillo) {
        Location escaleras = new Location("Escalera");
        pasaje.addDoor(escaleras, null,"Escalera",new EnterAction("use"));
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

    private void createGame(Player player,Location pasillo, Location salon1,
                            Location salon2, Location salonTres, Location acceso, Location biblioteca, Location sotano, Location afuera) {
        game.addRoom(pasillo);
        game.addRoom(salon1);
        game.addRoom(salon2);
        game.addRoom(salonTres);
        game.addRoom(acceso);
        game.addRoom(biblioteca);
        game.addRoom(sotano);
        game.addRoom(afuera);
        game.addCondition(new RoomCondition(afuera, true));
        game.makeLocationsAdjacent(salon1, pasillo, null, "goto");
        game.makeLocationsAdjacent(acceso, pasillo, null, "goto");
        game.makeLocationsAdjacent(acceso, biblioteca, null,"goto");
        game.makeLocationsAdjacent(salonTres, pasillo, null, "goto");
        game.makeLocationsAdjacent(salon2, pasillo, null, "goto");
    }

    private void setPlayerInitialInventory(Player player, int nroFoto) throws MaxInventoryException {
        player.setMaxInventory(4);
        Actionable lapicera = new Actionable("Lapicera" + nroFoto);
        Actionable foto = new Actionable("Foto" + nroFoto);
        addPickDrop(foto, player);
        foto.addAction(new MoveItemAction(true, false, "put"));
        player.addItem(foto);
        player.addItem(lapicera);
    }

    private void createSotano(Location sotano, Location afuera, Actionable martillo) {
        //Location sotano = new Location("Sotano");
        sotano.addDoor(afuera, martillo, "Ventana", new EnterAction("break"));
        //return sotano;
    }

    private Location createPasaje(Location biblioteca) {
        Location pasaje = new Location("Pasaje");
        biblioteca.addDoor(pasaje,null,"LibroViejo",new EnterAction("move"));
        return pasaje;
    }

    private void createBiblioteca(Location biblioteca, Player player, Location acceso, Location pasaje) {
        //Location biblioteca = new Location("Biblioteca");
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
        //return biblioteca;
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

    private void createSalonUno(Location salonUno, Player player, Location pasillo, Location acceso, Location biblioteca, Location salonTres) {
        //Location salonUno = new Location("Salon1");
        salonUno.addItem(new Actionable("mesa"));
        salonUno.addItem(new Actionable("vaso1"));
        salonUno.addItem(new Actionable("vaso2"));
        salonUno.addItem(new Actionable("silla1"));
        salonUno.addItem(new Actionable("silla2"));
        salonUno.addItem(new Actionable("cuadroTren"));
        createUsableItems(player, salonUno, acceso, biblioteca, salonTres, pasillo);


        //return salonUno;
    }

    private void createSalonDos(Location salon2, Player player, Location pasillo) {
        //Location salon2 = new Location("Salon2");
        Actionable martillo = new Actionable("Martillo");
        addPickDrop(martillo, player);
        salon2.addItem(martillo);
        salon2.addItem(new Actionable("Destornillador1"));
        salon2.addItem(new Actionable("Destornillador2"));
        //return salon2;
    }

    private Container createCuadroBarco(Location salonUno) {
        Container cuadroBarco = new Container("CuadroBarco",1);
        //cuadroBarco.addAction(new OpenAction("move"));
        cuadroBarco.addAction(new OpenCloseContainerAction("move", Container.openStatus));
        salonUno.addItem(cuadroBarco);
        return cuadroBarco;
    }

    private void createUsableItems(Player player, Location salonUno, Location acceso, Location biblioteca, Location salonTres, Location pasillo) {
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
        Actionable licor = new Actionable("licor");
        salonUno.addItem( licor);

        createLicorCredentialConditions(player, acceso, credencial, licor, biblioteca, pasillo);
    }

    private void createLicorCredentialConditions(Player player, Location acceso,
                                                 Container credencial, Actionable licor, Location biblioteca, Location pasillo) {

        addPickDrop(licor, player);
        //List<Actionable> list = new ArrayList<>();
        //list.add(foto);
        MoveItemAction show = new MoveItemAction(true,false,"show");
        //show.addCondition(new HasItemsWithItemsCondition(list, credencial, true));
        credencial.addAction(show);
        createAccesoBiblioteca(licor, credencial, acceso, biblioteca, pasillo);

    }

    private void createAccesoBiblioteca(Actionable licor, Container credencial, Location acceso, Location biblioteca, Location pasillo) {

        Container bibliotecario = new Container("Bibliotecario",5);
        acceso.addItem(bibliotecario);
        List<Actionable> listLicor = new ArrayList<>();
        listLicor.add(licor);
        MoveItemAction moveItemAction = new MoveItemAction(true, false, "move");
        ItemStatusAction itemStatusAction = new ItemStatusAction(new Status("asleep"),bibliotecario);
        itemStatusAction.addCondition(new HasItemsWithItemsCondition(listLicor,bibliotecario,true));
        AddTaskAction taskAction = new AddTaskAction("wakeUp",createWakeUpTask(game,120000,0,bibliotecario));
        AddTaskAction taskAction2 = new AddTaskAction("moveToNextRoom",createScheduledTask(game,120000 + 240000,240000,bibliotecario));
        ComplexAction action = new ComplexAction("give");
        action.addAction(moveItemAction);
        action.addAction(itemStatusAction);
        action.addAction(taskAction);
        action.addAction(taskAction2);
        licor.addAction(action);

        biblioteca.addEnterCondition(new HasItemsWithItemsCondition(listLicor,bibliotecario,true));
        createBibliotecaEnterConditions(credencial, bibliotecario, biblioteca, pasillo);
    }

    private void createBibliotecaEnterConditions(Container credencial, Container bibliotecario, Location biblioteca, Location pasillo) {
        createPlayerWithConditionsBiblioteca(credencial, bibliotecario, biblioteca, pasillo, 1);
        createPlayerWithConditionsBiblioteca(credencial, bibliotecario, biblioteca, pasillo, 2);
        createPlayerWithConditionsBiblioteca(credencial, bibliotecario, biblioteca, pasillo, 3);
        createPlayerWithConditionsBiblioteca(credencial, bibliotecario, biblioteca, pasillo, 4);
    }

    private void createPlayerWithConditionsBiblioteca(Container credencial, Container bibliotecario, Location biblioteca, Location pasillo, int nroPlayer) {
        List<Actionable> listCredencial = new ArrayList<>();
        listCredencial.add(credencial);

        ComplexCondition complexCondition = new ComplexCondition();
        Player player = game.getPlayer("Player " + nroPlayer);
        Actionable foto = player.getItem("Foto" + nroPlayer);
        complexCondition.addCondition(new HasItemsWithItemsCondition(listCredencial, bibliotecario, true));
        List<Actionable> listCredencialConFoto = new ArrayList<>();
        listCredencialConFoto.add(foto);
        complexCondition.addCondition(new HasItemsWithItemsCondition(listCredencialConFoto, credencial, true));
        complexCondition.addCondition(new isPlayerCondition(player));
        biblioteca.addEnterCondition(complexCondition);
    }

    private void addPickDrop(Actionable actionable, Player player) {
        actionable.addAction(new MoveItemAction(false,true,"pick"));
        actionable.addAction(new MoveItemAction(true,false,"drop"));
    }

    private ScheduledTask createScheduledTask(Game game, int delay, int period, Actionable item) {
        return new ScheduledTask(game,period,delay) {
            @Override
            public void run() {
                try {
                    System.out.println("Moviendo...");
                    Location location = game.findItemLocation(item.getName());
                    location.removeItem(item.getName());
                    Location newLocation = location.getRandomAdjacentLocation();
                    newLocation.addItem(item);
                    game.addMessage("El bibliotecario se movio a " + newLocation.getName());
                    updateNextExecution();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private ScheduledTask createWakeUpTask(Game game, int delay, int period,  Actionable item) {
        return new ScheduledTask(game,period,delay) {
            @Override
            public void run() {
                try {
                    Status status = new Status("angry");
                    item.setNewStatus(status);
                    game.addLoseCondition(new RoomItemStatusCondition(item, status.getID()));
                    game.addMessage("El bibliotecario se desperto y esta enojado!");
                    updateNextExecution();
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