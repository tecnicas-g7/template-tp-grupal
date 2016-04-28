package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.conditions.Condition;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.*;

/**
 * Created by fran on 24/04/16.
 */
public class Game {

    private List<Room> rooms;
    private Player player;
    private List<Condition> conditions;

    public Game(Player player) {
        this.rooms = new ArrayList<Room>();
        this.player = player;
        this.conditions = new ArrayList<>();
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public Player getPlayer() {
        return this.player;
    }

    public String executeActionOnItem(String[] tokens) throws WrongItemActionException {
        String objectName;
        try {
            objectName = tokens[1];
        } catch (Exception e) {
            return "You have to select an item!";
        }

        try {
            ContainerComponent item = findItem(objectName);
            if (item != null) {
                return item.executeAction(tokens,this.player);
            }
        } catch (ItemNotFoundException ine) {
            return ine.getMessage();
        }
        return null;
    }

    public ContainerComponent findItem(String objectName) throws ItemNotFoundException {
        try {
            return this.player.getItem(objectName);
        } catch (ItemNotFoundException e) {
            return this.player.getRoom().getItem(objectName);
        }
    }

    public String look() {
        return this.player.getRoom().look();

    }

    public String showInventory() {
        return this.player.showInventory();
    }

    public String enter(String[] tokens) {
        Room origin = player.getRoom();
        Iterator<HashMap.Entry<String, Door>> it = origin.getDoorsIterator();
        while (it.hasNext()) {
            Door door = it.next().getValue();
            Room destination = door.getDestination();
            if (door.getName().equals(tokens[1]) && origin.validLeaveConditions(player) && destination.validEnterConditions(player)) {
                return player.enter(door);
            }
        }
        return "The door doesn't open ...";
    }

    public String cross() {
        Room origin = player.getRoom();
        Room destination = origin.getDoorsIterator().next().getValue().getDestination();
        if (validateEnterAndLeaveConditions(origin, destination)) {
            player.cross(destination);
        } else {
            System.out.print("You cannot do that!");
        }
        return "";
    }

    private boolean validateEnterAndLeaveConditions(Room origin, Room destination) {
        return origin.validLeaveConditions(player) && destination.validEnterConditions(player);
    }

    public void addCondition(Condition condition) {
        this.conditions.add(condition);
    }

    public boolean verifyVictory() {
        for (Condition condition : this.conditions) {
            if (!condition.isValid(this.player)) {
                return false;
            }
        }
        System.out.print("You have won the game!");
        return  true;
    }

    public String itemHelp(String[] tokens) {
        if (tokens.length <= 1) {
            return "You have to select an item!";
        }
        String name = tokens[1];
        try {
            ContainerComponent item = findItem(name);
            if (item != null) {
                return item.showActions();
            }
        } catch (ItemNotFoundException ine) {
            return "You can enter doors";
        }
        return "You can enter doors";
    }
}

