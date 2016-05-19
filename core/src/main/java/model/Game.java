package model;

import exceptions.ItemNotFoundException;
import exceptions.WrongItemActionException;
import game.Location;
import game.Player;
import game.conditions.Condition;
import game.items.Actionable;
import game.utils.Messages;

import java.util.ArrayList;
import java.util.List;


public class Game {

    private List<Location> rooms;
    private Player player;
    private List<Condition> conditions;

    public Game(Player player) {
        this.rooms = new ArrayList<>();
        this.player = player;
        this.conditions = new ArrayList<>();
    }

    public void addRoom(Location room) {
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
            return Messages.getMessage("selectItemNeededMessage");
        }

        try {
            Actionable item = findItem(objectName);
            if (item != null) {
                return item.executeAction(tokens,this.player);
            }
        } catch (ItemNotFoundException ine) {
            return ine.getMessage();
        }
        return null;
    }

    private Actionable findItem(String objectName) throws ItemNotFoundException {
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
        return this.getPlayer().showInventory();
    }

    /*String enter(String[] tokens) {
        Location origin = player.getRoom();
        Iterator<HashMap.Entry<String, Linker>> it = origin.getDoorsIterator();
        while (it.hasNext()) {
            Linker door = it.next().getValue();
            Location destination = door.getDestination();
            if (door.getName().equals(tokens[1]) && origin.validLeaveConditions(player) && destination.validEnterConditions(player)) {
                return player.enter(door);
            }
        }
        return Messages.getMessage("youCantDoThatMessage");
    }*/

    private boolean validateEnterAndLeaveConditions(Location origin, Location destination) {
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
        System.out.print(Messages.getMessage("endMessage"));
        return  true;
    }

    public String itemHelp(String[] tokens) {
        if (tokens.length <= 1) {
            return Messages.getMessage("selectItemNeededMessage");

        }
        String name = tokens[1];
        try {
            Actionable item = findItem(name);
            if (item != null) {
                return item.showActions();
            }
        } catch (ItemNotFoundException ine) {
            return Messages.getMessage("enterDoorMessage");
        }
        return Messages.getMessage("enterDoorMessage");
    }

}
