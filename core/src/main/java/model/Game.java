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
import java.util.HashMap;
import java.util.List;
import java.util.*;


public class Game {

    private List<Location> rooms;
    //private List<Condition> conditions;
    //private List<Condition> loseConditions;

    private Map<String,List<Condition>> conditions;
    private Map<String,List<Condition>> loseConditions;

    private Player activePlayer;
    private HashMap<String,Player> players;

    public ArrayList<String> messages;

    public Game(Player activePlayer) {
        this.rooms = new ArrayList<>();
        this.activePlayer = activePlayer;
//        this.conditions = new ArrayList<>();
//        this.loseConditions = new ArrayList<>();

        this.conditions = new HashMap<>();
        this.loseConditions = new HashMap<>();

        this.conditions.put(this.activePlayer.getName(), new ArrayList<>());
        this.loseConditions.put(this.activePlayer.getName(), new ArrayList<>());

        this.players = new HashMap<>();
        this.players.put(this.activePlayer.getName(),this.activePlayer);

        this.messages = new ArrayList<>();
    }

    public void addRoom(Location room) {
        this.rooms.add(room);
    }

    public Player getActivePlayer() {
        return this.activePlayer;
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
                return item.executeAction(tokens,this.activePlayer);
            }
        } catch (ItemNotFoundException ine) {
            return ine.getMessage();
        }
        return null;
    }

    public Actionable findItem(String objectName) throws ItemNotFoundException {
        try {
            return this.activePlayer.getItem(objectName);
        } catch (ItemNotFoundException e) {
            return this.activePlayer.getRoom().getItem(objectName);
        }
    }

    public String look() {
        return this.activePlayer.getRoom().look();
    }

    public String showInventory() {
        return this.getActivePlayer().showInventory();
    }

    private boolean validateEnterAndLeaveConditions(Location origin, Location destination) {
        return origin.validLeaveConditions(activePlayer) && destination.validEnterConditions(activePlayer);
    }

    public void addCondition(Condition condition) {
        for (Player player : players.values()) {
            this.addCondition(condition, player);
        }
    }

    public void addLoseCondition(Condition condition) {
        for (Player player : players.values()) {
            this.addLoseCondition(condition, this.getActivePlayer());
        }
    }

    public void addCondition(Condition condition, Player player) {
        this.conditions.get(player.getName()).add(condition);
    }

    public void addLoseCondition(Condition condition, Player player) {
        this.loseConditions.get(player.getName()).add(condition);
    }


    public boolean verifyVictory() {
        return verifyVictory(activePlayer);
    }

    public boolean verifyVictory(Player player) {
        for (Condition condition : this.conditions.get(player.getName())) {
            if (!condition.isValid(player)) {
                return false;
            }
        }
        return true;
    }

    public List<String> checkWinners() {
        List<String> winners = new ArrayList<>();
        for (Player player : players.values()) {
            if (verifyVictory(player)) {
                winners.add(player.getName());
            }
        }
        return winners;
    }

    public List<String> checkLosers() {
        List<String> losers = new ArrayList<>();
        for (Player player : players.values()) {
            if (gameOver(player)) {
                losers.add(player.getName());
            }
        }
        return losers;
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
            return activePlayer.execute(tokens);
        } catch (WrongItemActionException e) {
            return Messages.getMessage("ActionNotSupported");
        }
    }

    public boolean gameOver(Player player) {
        for (Condition condition : this.loseConditions.get(player.getName())) {
            if (condition.isValid(player)) {
                return true;
            }
        }
        return false;
    }

    public boolean gameOver() {
        return gameOver(this.activePlayer);
    }


    public void addTask(ScheduledTask task, int delay, int period) {
        Timer timer = new Timer();
        if (period == 0) {
            timer.schedule(task, delay);
        }
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public void addPlayer(Player player) {
        players.put(player.getName(),player);
        this.conditions.put(player.getName(), new ArrayList<>());
        this.loseConditions.put(player.getName(), new ArrayList<>());
    }

    public void setActivePlayer(String player) {
        this.activePlayer = players.get(player);
    }

    public Player getFreePlayer() {
        for (Player player : players.values()) {
            if (!player.isPlaying()) {
                player.setPlaying(true);
                return player;
            }
        }
        return null;
    }


    public boolean hasPlayersPlaying() {
        for (Player player : players.values()) {
            if (player.isPlaying()) {
                return true;
            }
        }
        return false;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public ArrayList<String> getMessages() {
        ArrayList<String> returnMessages = new ArrayList<>(messages);
        if (messages.size() > 0) {
            System.out.println("Hello");
        }
        messages.clear();

        return returnMessages;
    }
}