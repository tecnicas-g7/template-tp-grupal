
import game.Location;
import game.Player;
import game.actions.*;
import game.conditions.InventoryCondition;
import game.conditions.PlayerStateCondition;
import game.conditions.RoomCondition;
import game.items.Actionable;
import game.items.Container;
import game.states.*;
import model.Game;
import model.GameBuilder;










import java.util.*;

/**
 * Created by nicol on 18/5/2016.
 */

@SuppressWarnings("CPD-START")
public class TreasureGame implements GameBuilder {

    public Game build() {
        Location room1 = new Location("Room1");
        Location room2 = new Location("Room2");
        Player player = createPlayer(room1);
        createComponentsFirstRoom(room1, room2, player);

        Location room3 = new Location("Room3");
        Location room4 = new Location("Room4");
        Location room5 = new Location("Room5");

        createComponentsSecondRoom(room2, room3, room4, player);
        createComponentsThirdRoom(room3, room4, room5, player);

        Actionable treasure = new Actionable("treasure");
        createComponentsFifthRoom(room5, treasure, player);

        player.setMaxInventory(2);

        Game game = new Game(player);

        addRooms(game, room1, room2, room3, room4, room5);

        addConditions(game, room3, treasure);

        return game;
    }

    @Override
    public String getName() {
        return "treasureGame";
    }

    private void addConditions(Game game, Location room, Actionable treasure) {
        List<Actionable> items = new ArrayList<>();
        items.add(treasure);
        game.addCondition(new InventoryCondition(items, true));

        game.addLoseCondition(new PlayerStateCondition(new StatePlayer("poisoned")));

        game.addLoseCondition(new RoomCondition(room, true));

    }

    private static void addRooms(Game game, Location room1, Location room2, Location room3, Location room4, Location room5) {
        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);
        game.addRoom(room4);
        game.addRoom(room5);
    }

    private static void createComponentsFirstRoom(Location room1, Location room2, Player player) {
        Actionable key = new Actionable("key");
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
        Actionable key = new Actionable("key2");
        addPickDrop(key, player);
        box.addComponent(key);
        //box.yesPoison();
        Actionable antidote = createAntidote(player);
        trunk.addComponent(antidote);
        trunk.addComponent(box);
        room3.addItem(trunk);
        EnterAction enterAction = new EnterAction("enter");
        room4.addDoor(room5, key,enterAction);
        room5.addDoor(room4,null,enterAction);
        //room3.addLeaveCondition(new PlayerStateCondition(Player.Status.poisoned));
    }

    private static Container createBox() {
        Container box = new Container("box",1);
        //box.addAction(new CloseAction("close"));
        box.addAction(new OpenCloseContainerAction("close"));
        PlayerStatusAction firstAction = new PlayerStatusAction(new StatePlayer("poisoned"), "oops");
        //OpenAction secondAction = new OpenAction("open");
        OpenCloseContainerAction secondAction = new OpenCloseContainerAction("open");
        //OpenAction secondAction = new OpenAction("open");
        ComplexAction action = new ComplexAction("open");
        action.addAction(firstAction);
        action.addAction(secondAction);
        box.addAction(action);
        return box;
    }

    private static Actionable createAntidote(Player player) {
        Actionable antidote = new Actionable("antidote");
        PlayerStatusAction firstAction = new PlayerStatusAction(new StatePlayer("alive"), "drink");
        MoveItemAction secondAction = new MoveItemAction(player,new Container("void",666),"drop");
        ComplexAction action = new ComplexAction("drink");
        action.addAction(firstAction);
        action.addAction(secondAction);
        antidote.addAction(action);
        addPickDrop(antidote,player);
        return antidote;
    }

    private static void createComponentsFifthRoom(Location room5, Actionable treasure, Player player) {
        addPickDrop(treasure, player);
        room5.addItem(treasure);
    }

    private static void addPickDrop(Actionable item, Player player) {
        item.addAction(new MoveItemAction(null,player,"pick"));
        item.addAction(new MoveItemAction(player,null,"drop"));
    }

    private static void addOpenClose(Container container) {
        //container.addAction(new OpenAction("open"));
        //container.addAction(new CloseAction("close"));
        container.addAction(new OpenCloseContainerAction("open"));
        container.addAction(new OpenCloseContainerAction("close"));
    }

    @SuppressWarnings("CPD-END")

    public String getHelp() {
        return "The player will look for the treasure!";
    }

}