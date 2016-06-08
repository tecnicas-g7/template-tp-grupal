package model;

import exceptions.ItemNotFoundException;
import exceptions.WrongItemActionException;
import game.actions.EnterAction;
import game.Location;
import game.Player;
import game.conditions.Condition;
import game.items.Actionable;
import game.tasks.ScheduledTask;
import game.utils.Messages;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class Game {

    private List<Location> rooms;
    private Player player;
    private List<Condition> conditions;
    private List<Condition> loseConditions;

    public Game(Player player) {
        this.rooms = new ArrayList<>();
        this.player = player;
        this.conditions = new ArrayList<>();
        this.loseConditions = new ArrayList<>();
    }

    public void addRoom(Location room) {
        this.rooms.add(room);
    }

    public Player getPlayer() {
        return this.player;
    }

    public Location findItemLocation(Actionable item) {
        for (Location location : rooms) {
            if (location.getItem(item.getName()) != null) {
                return location;
            }
        }
        return null;
    }

    public void makeLocationsAdjacent(Location room1, Location room2, Actionable key, String actionName) {
        EnterAction enterAction = new EnterAction(actionName);
        room1.addDoor(room2,key,room2.getName(),enterAction);
        room2.addDoor(room1,key,room1.getName(),enterAction);
    }

    public void makeLocationsAdjacent(Location room1, Location room2, Actionable key) {
        EnterAction enterAction = new EnterAction("enter");
        room1.addDoor(room2,key,enterAction);
        room2.addDoor(room1,key,enterAction);
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

    private boolean validateEnterAndLeaveConditions(Location origin, Location destination) {
        return origin.validLeaveConditions(player) && destination.validEnterConditions(player);
    }

    public void addCondition(Condition condition) {
        this.conditions.add(condition);
    }

    public void addLoseCondition(Condition condition) {
        this.loseConditions.add(condition);
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

    public String executeAction(String[] tokens) {
        try {
            return player.execute(tokens);
        } catch (WrongItemActionException e) {
            return Messages.getMessage("ActionNotSupported");
        }
    }

    public boolean gameOver() {
        if (loseConditions.size() > 0) {
            for (Condition condition : this.loseConditions) {
                if (condition.isValid(this.player)) {
                    return true;
                }
            }
            System.out.print(Messages.getMessage("GameOver"));
            return false;
        }
        return false;
    }

    public void addTask(ScheduledTask task, int delay, int period) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, delay, period);
    }
}

