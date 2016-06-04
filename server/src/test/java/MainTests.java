/*import game.Controller;
import game.Location;
import game.Player;
import game.actions.EnterAction;
import game.actions.MoveItemAction;
import game.conditions.InventoryCondition;
import game.conditions.RoomCondition;
import game.items.Actionable;
import game.items.Container;
import game.items.Item;
import game.items.Linker;
import model.Game;
import org.junit.Assert;
import org.junit.Test;
import server.driver.Driver;
import server.driver.GameDriver;
import server.GamePaths;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTests {

    private GameDriver driver;

    public void initGame(String jarPath) {
        driver = new Driver();
        try {
            driver.initGame(jarPath);
        } catch (Exception e) {
            //
        }
    }

    private Item createItemsWithCondition(String nameItem, Player player) {
        Item itemWithActions = new Item(nameItem);
        itemWithActions.addAction(new MoveItemAction(null, player, "pick"));
        itemWithActions.addAction(new MoveItemAction(player, null, "drop"));
        return itemWithActions;
    }

    private void makeLocationsAdjacent(Location room1, Location room2, Item key) {
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2, key, enterAction);
        room2.addDoor(room1, key, enterAction);
    }

    private Game getGameCondition() {
        Location room1 = new Location("Room1");
        Player player = new Player(room1);
        Item key = createItemsWithCondition("key", player);
        Item mouse = createItemsWithCondition("mouse", player);
        Item stick = createItemsWithCondition("stick", player);

        room1.addItem(key);
        room1.addItem(mouse);
        room1.addItem(stick);
        Location room2 = new Location("Room2");
        makeLocationsAdjacent(room1, room2, key);

        player.setMaxInventory(2);
        Game game = new Game(player);
        game.addRoom(room1);
        game.addRoom(room2);
        List<Actionable> items = new ArrayList<>(Arrays.asList(stick, key));
        game.addCondition(new InventoryCondition(items, true));
        game.addCondition(new RoomCondition(room2, true));
        return game;
    }

    @Test
    public void stickQuest() {

        initGame(GamePaths.getGamePath("gameStick"));
        driver.sendCommand("pick stick");

        assert (Controller.GameState.Win == driver.getGameState());
    }

    @Test
    public void enterRoom() {

        initGame(GamePaths.getGamePath("gameEnterRoom"));
        driver.sendCommand("pick key");
        driver.sendCommand("enter door1");

        assert(Controller.GameState.Win == driver.getGameState());
    }

    @Test
    public void boxGame() {

        initGame(GamePaths.getGamePath("gameBox"));
        String command = "open box";
        driver.sendCommand(command);
        String command2 = "pick key";
        driver.sendCommand(command2);
        String command3 = "enter door1";
        driver.sendCommand(command3);

        assert(Controller.GameState.Win == driver.getGameState());
    }

    @Test
    public void cursedItem() {

        initGame(GamePaths.getGamePath("gameCursedItem"));
        String command = "pick cursed_item";
        driver.sendCommand(command);
        String command2 = "enter door1";
        driver.sendCommand(command2);
        String command3 = "talk thief";
        driver.sendCommand(command3);
        String command4 = "enter door1";
        driver.sendCommand(command4);


        assert(Controller.GameState.Win == driver.getGameState());
    }

    @Test
    public void hanoiTower() {

        initGame(GamePaths.getGamePath("gameHanoiTower"));
        String command = "move stack1 stack3";
        driver.sendCommand(command);
        String command2 = "move stack1 stack2";
        driver.sendCommand(command2);
        String command3 = "move stack3 stack2";
        driver.sendCommand(command3);
        String command4 = "move stack1 stack3";
        driver.sendCommand(command4);
        String command5 = "move stack2 stack1";
        driver.sendCommand(command5);
        String command6 = "move stack2 stack3";
        driver.sendCommand(command6);
        String command7 = "move stack1 stack3";
        driver.sendCommand(command7);

        assert(Controller.GameState.Win == driver.getGameState());

    }

    @Test
    public void treasure() {

        initGame(GamePaths.getGamePath("gameTreasureBox"));

        String command = "open box";
        driver.sendCommand(command);
        String command2 = "pick key";
        driver.sendCommand(command2);
        String command3 = "enter door1";
        driver.sendCommand(command3);
        String command4 = "enter door2";
        driver.sendCommand(command4);
        String command5 = "open trunk";
        driver.sendCommand(command5);
        String command6 = "drop key";
        driver.sendCommand(command6);
        String command7 = "pick antidote";
        driver.sendCommand(command7);
        interpretTreasureCommands();

        assert(Controller.GameState.Win == driver.getGameState());

    }

    private void interpretTreasureCommands() {

        String command = "open box";
        driver.sendCommand(command);
        command = "pick key2";
        driver.sendCommand(command);
        command = "drink antidote";
        driver.sendCommand(command);
        command = "enter door1";
        driver.sendCommand(command);
        command = "enter door3";
        driver.sendCommand(command);
        command = "enter door2";
        driver.sendCommand(command);
        command = "pick treasure";
        driver.sendCommand(command);
    }

    @Test
    public void cantEnterDoor() {
        Item key = new Item("key");
        Location room1 = new Location("Room 1");
        Container container = new Container("Box",1);
        container.addComponent(key);
        room1.addItem(container);
        Location room2 = new Location("Room 2");
        Player player2 = new Player(room2);
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2,key,enterAction);
        room2.addDoor(room1, key, enterAction);
        Linker door = room1.getDestinationDoor(room2);
        try {
            container.openContainer(player2);
            player2.addItem(room2.getItem("key"));
        } catch (Exception e) {
            //Do nothing
        }
        Player player = new Player(room1);
        player.enter(door);
        assertFalse(player.checkVictory(player2));
    }

    @Test
    public void cantTakeMoreItems() {
        Game dropGame = getGameCondition();
        Controller controller = new Controller(dropGame);
        try {
            String command = "pick key";
            controller.interpretCommand(command);
            command = "pick mouse";
            controller.interpretCommand((command));
            command = "pick stick";
            controller.interpretCommand((command));
            command = "drop mouse";
            controller.interpretCommand((command));
            command = "pick stick";
            controller.interpretCommand((command));
            command = "enter door1";
            controller.interpretCommand((command));

        } catch (Exception e) {
            //Do nothing
        }

        assertTrue(dropGame.verifyVictory());
    }

    @Test
    public void lookAroundTest() {
        Item key = new Item("key");
        Location room1 = new Location("Room 1");
        Container container = new Container("Box",1);
        container.addComponent(key);
        room1.addItem(container);
        Container container1 = new Container("Baul",10);
        Container container2 = new Container("Box2",1);
        Item stick = new Item("Stick");
        container2.addComponent(stick);
        container1.addComponent(container2);
        room1.addItem(container1);
        Player player = new Player(room1);
        player.openContainer("Baul");
        System.out.println(room1.look());
        assertTrue(true);
    }


    private void simpleCross( String animal, String moveTo) {
        driver.sendCommand("take " + animal);
        driver.sendCommand("cross " + moveTo);
        driver.sendCommand("leave " + animal);
    }

    @Test
    public void riverCrossingVictory() {


        initGame(GamePaths.getGamePath("gameRiverCrossing"));

        simpleCross("sheep", "north-shore");

        driver.sendCommand("cross south-shore");

       //driver.sendCommand("cross south-shore");

        simpleCross("wolf", "north-shore");

        driver.sendCommand("take sheep");
        driver.sendCommand("cross south-shore");
        driver.sendCommand("leave sheep");


        simpleCross("cabbage", "north-shore");

        driver.sendCommand("cross south-shore");

       //driver.sendCommand("cross south-shore");

        simpleCross("sheep", "north-shore");


        assert(Controller.GameState.Win == driver.getGameState());

    }

    @Test
    public void riverCrossingFail() {


        initGame(GamePaths.getGamePath("gameRiverCrossing"));
        String takeSheep = "take sheep";
        String leaveSheep = "leave sheep";

        String takeWolf = "take wolf";
        String leaveWolf = "leave wolf";

        String northShore = "cross north-shore";
        String crossSouth = "cross south-shore";

        driver.sendCommand(takeSheep);
        driver.sendCommand(northShore);
        driver.sendCommand(leaveSheep);

        driver.sendCommand(crossSouth);

        driver.sendCommand(takeWolf);
        driver.sendCommand(northShore);
        driver.sendCommand(leaveWolf);


        assert(Controller.GameState.Win != driver.getGameState());
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
*/