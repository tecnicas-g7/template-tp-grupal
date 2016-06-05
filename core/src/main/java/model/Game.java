package model;

import exceptions.ItemNotFoundException;
import exceptions.WrongItemActionException;
import game.Location;
import game.Player;
import game.conditions.Condition;
import game.items.Actionable;
import game.utils.Messages;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Game {

    private List<Location> rooms;
    private List<Condition> conditions;
    private List<Condition> loseConditions;

    private Player activePlayer;
    private HashMap<String,Player> players;

    public Game(Player activePlayer) {
        this.rooms = new ArrayList<>();
        this.activePlayer = activePlayer;
        this.conditions = new ArrayList<>();
        this.loseConditions = new ArrayList<>();

        this.players = new HashMap<>();
        this.players.put(this.activePlayer.getName(),this.activePlayer);
    }

    public void addRoom(Location room) {
        this.rooms.add(room);
    }

    public Player getActivePlayer() {
        return this.activePlayer;
    }

    public String executeActionOnItem(String[] tokens) throws WrongItemActionException {
        System.out.println("Activo: " + activePlayer.getName());
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

    private Actionable findItem(String objectName) throws ItemNotFoundException {
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
        this.conditions.add(condition);
    }

    public void addLoseCondition(Condition condition) {
        this.loseConditions.add(condition);
    }

    public boolean verifyVictory() {
        for (Condition condition : this.conditions) {
            if (!condition.isValid(this.activePlayer)) {
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
            return activePlayer.execute(tokens);
        } catch (WrongItemActionException e) {
            return Messages.getMessage("ActionNotSupported");
        }
    }

    public boolean gameOver() {
        if (loseConditions.size() > 0) {
            for (Condition condition : this.loseConditions) {
                if (condition.isValid(this.activePlayer)) {
                    return true;
                }
            }
            System.out.print(Messages.getMessage("GameOver"));
            return false;
        }
        return false;
    }

    public void addPlayer(Player player) {
        players.put(player.getName(),player);
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
}

