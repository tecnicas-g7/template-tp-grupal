
import game.Location;
import game.Player;
import game.actions.*;
import game.conditions.InventoryCondition;
import game.conditions.PlayerStateCondition;
import game.items.Actionable;
import game.items.Container;
import game.items.Item;
import model.Game;
import model.GameBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicol on 18/5/2016.
 */
public class Configuration implements GameBuilder{

    public Game build() {

        Location room1 = new Location("Room1");
        Location room2 = new Location("Room2");
        Player player = new Player(room1);
        createComponentsFirstRoom(room1, room2, player);

        Location room3 = new Location("Room3");
        Location room4 = new Location("Room4");
        Location room5 = new Location("Room5");

        createComponentsSecondRoom(room2, room3, room4, player);
        createComponentsThirdRoom(room3, room4, room5, player);

        Item treasure = new Item("treasure");

        createComponentsFifthRoom(room5, treasure, player);


        player.setMaxInventory(2);

        Game game = new Game(player);

        //  addRooms(game, room1, room2, room3, room4, room5);

        List<Actionable> items = new ArrayList<Actionable>();
        items.add(treasure);

        game.addCondition(new InventoryCondition(items, true));

        return game;
    }

    @Override
    public String getName() {
        return "treasureBox";
    }

    private static void addRooms(Game game, Location room1, Location room2, Location room3, Location room4, Location room5) {
        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);
        game.addRoom(room4);
        game.addRoom(room5);
    }

    private static void createComponentsFirstRoom(Location room1, Location room2, Player player) {
        Item key = new Item("key");
        addPickDrop(key, player);
        Container box = new Container("box",1);
        addOpenClose(box);
        box.addComponent(key);
        room1.addItem(box);
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2, key,enterAction);
        room2.addDoor(room1, null,enterAction);
    }

    private static void createComponentsSecondRoom(Location room2, Location room3, Location room4, Player player) {
        EnterAction enterAction = new EnterAction("enter");
        room2.addDoor(room3, null, enterAction);
        room2.addDoor(room4, null, enterAction);
        room3.addDoor(room2,null, enterAction);
        room4.addDoor(room2,null, enterAction);
    }

    private static void createComponentsThirdRoom(Location room3, Location room4, Location room5, Player player) {
        Container trunk = new Container("trunk", 10);
        addOpenClose(trunk);
        Container box = createBox();
        Item key = new Item("key2");
        addPickDrop(key, player);
        box.addComponent(key);
        //box.yesPoison();
        Item antidote = createAntidote(player);
        trunk.addComponent(antidote);
        trunk.addComponent(box);
        room3.addItem(trunk);
        EnterAction enterAction = new EnterAction("enter");
        room4.addDoor(room5, key,enterAction);
        room5.addDoor(room4,null,enterAction);
        room3.addLeaveCondition(new PlayerStateCondition(Player.Status.poisoned));
    }

    private static Container createBox() {
        Container box = new Container("box",1);
        box.addAction(new CloseAction("close"));
        PlayerStatusAction firstAction = new PlayerStatusAction(Player.Status.poisoned, "oops");
        OpenAction secondAction = new OpenAction("open");
        ComplexAction action = new ComplexAction("open");
        action.addAction(firstAction);
        action.addAction(secondAction);
        box.addAction(action);
        return box;
    }

    private static Item createAntidote(Player player) {
        Item antidote = new Item("antidote");
        PlayerStatusAction firstAction = new PlayerStatusAction(Player.Status.alive, "drink");
        MoveItemAction secondAction = new MoveItemAction(player,new Container("void",666),"drop");
        ComplexAction action = new ComplexAction("drink");
        action.addAction(firstAction);
        action.addAction(secondAction);
        antidote.addAction(action);
        addPickDrop(antidote,player);
        return antidote;
    }

    private static void createComponentsFifthRoom(Location room5, Item treasure, Player player) {
        addPickDrop(treasure, player);
        room5.addItem(treasure);
    }

    private static void addPickDrop(Item item, Player player) {
        item.addAction(new MoveItemAction(null,player,"pick"));
        item.addAction(new MoveItemAction(player,null,"drop"));
    }

    private static void addOpenClose(Container container) {
        container.addAction(new OpenAction("open"));
        container.addAction(new CloseAction("close"));
    }

    public String getHelp() {
        return "The player will look for the treasure!";
    }

}
