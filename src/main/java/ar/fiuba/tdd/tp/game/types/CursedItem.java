package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;
import ar.fiuba.tdd.tp.game.actions.*;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.conditions.RoomCondition;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.utils.Messages;

import java.util.ArrayList;
import java.util.List;

/*
Created by fran on 27/04/16.
*/

@SuppressWarnings("CPD-START")
public class CursedItem implements GameFactory {

    public Game getGame() {
        Location room1 = new Location("Room1");
        Location room2 = new Location("Room2");

        Actionable cursedItem = new Actionable("cursed_item");


        room1.addItem(cursedItem);
        createItemsSecondRoom(room2);

        Location room3 = new Location("Room3");

        addRoomConditions(room2,room3,cursedItem);
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2,null,enterAction);

        room2.addDoor(room3,null,enterAction);

        Player player = createPlayer(room1);
        cursedItem.addAction(new MoveItemAction(null,player,"pick"));
        Game game = new Game(player);

        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);

        game.addCondition(new RoomCondition(room3, true));

        return game;
    }

    private void createItemsSecondRoom(Location room) {
        Actionable thief = new Actionable("thief");
        thief.addAction(createThiefAction());

        room.addItem(thief);
    }

    private static void addRoomConditions(Location room2, Location room3, Actionable cursedItem) {
        List<Actionable> items = new ArrayList<>();
        items.add(cursedItem);

        room2.addEnterCondition(new InventoryCondition(items, true));
        room3.addEnterCondition(new InventoryCondition(items, false));
    }

    private Action createThiefAction() {
        return new Action("talk") {
            @Override
            public String execute(String[] tokens, Player player, Actionable item) {
                player.clearInventory();
                return "Hi! \n The " + item.getName() + " " + Messages.getMessage("hasStolenYourItems");
            }
        };
    }

    @SuppressWarnings("CPD-END")

    public String getHelp() {
        return "The player needs to find the cursed artifact and get rid of it somehow to win";
    }

}
