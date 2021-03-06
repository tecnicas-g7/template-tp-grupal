package game;

import exceptions.ItemNotFoundException;
import exceptions.MaxInventoryException;
import exceptions.WrongItemActionException;
import game.actions.Action;
import game.items.Actionable;
import game.items.Linker;
import game.utils.Messages;
import game.states.State;
import game.states.Status;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*
Created by fran on 24/04/16.
*/

public class Player implements HasItems {

    private static final int DEFAULT_MAX_INVENTORY = 10;

    private HashMap<String,Actionable> inventory;
    private HashMap<String,Action> actions;
    private int maxInventory;
    private Location room;

    private State status;

    private String name;

    private boolean playing;

    public enum GameState {
        Lost, Win, InProgress
    }

    private GameState gameState;

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    public void clearInventory() {
        inventory.clear();
    }

    public boolean supports(String action) {
        return this.actions.containsKey(action);
    }

    public String execute(String[] tokens) throws WrongItemActionException {
        String actionName = tokens[0];
        if (!supports(actionName)) {
            throw new WrongItemActionException();
        }
        return actions.get(actionName).execute(tokens, this, null);
    }


    public String getName() {
        return name;
    }

    private void initialize() {
        this.inventory = new HashMap<>();
        this.maxInventory = DEFAULT_MAX_INVENTORY;
        this.status = new Status("ALIVE");
        this.actions = new HashMap<>();
        this.playing = false;
        this.gameState = GameState.InProgress;
    }

    public Player(Location room) {
        initialize();
        this.room = room;
        this.name = "Player1";
    }

    public Player(Location room, String name) {
        initialize();
        this.room = room;
        this.name = name;
    }

    public void setMaxInventory(int maxInventory) {
        this.maxInventory = maxInventory;
    }

    public void changeStatus(State newStatus) {
        this.status = newStatus;
    }

    public void addItem(Actionable item) throws MaxInventoryException {
        if (inventory.size() == maxInventory) {
            throw new MaxInventoryException();
        }
        item.setContainer(this);
        this.inventory.put(item.getName(), item);
    }

    public void removeItem(String name) {
        this.inventory.remove(name);
    }

    public Actionable getItem(String name) throws ItemNotFoundException {
        Actionable item = this.inventory.get(name);
        if (item != null) {
            return item;
        }
        throw new ItemNotFoundException();
    }

    public Location getRoom() {
        return this.room;
    }

    public State getStatus() {
        return this.status;
    }

    public String showInventory() {
        Set<String> items = this.inventory.keySet();
        String inventoryNames = "";
        for (String item : items) {
            inventoryNames = inventoryNames.concat(item + " ");
        }
        String output = "You have ";
        output = output.concat(inventoryNames);
        return output;
    }

    public int getInventorySize() {
        return this.inventory.size();
    }

    public HashMap<String,Actionable> getInventory() {
        return this.inventory;
    }

    private Iterator<Actionable> getInventoryIterator() {
        return this.inventory.values().iterator();
    }

    private boolean checkIdenticalInventory(Iterator<Actionable> it) {
        while (it.hasNext()) {
            Actionable item = it.next();
            if (!this.inventory.containsKey(item.getName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkVictory(Player winner) {
        Iterator<Actionable> it = winner.getInventoryIterator();
        return (winner.getRoom() == this.room && checkIdenticalInventory(it) && this.getInventorySize() == winner.getInventorySize());
    }

    public String enter(Linker door) {
        if (!door.isLocked()) {
            this.room = door.getDestination();
            return door.getName() + " " + Messages.getMessage("entered");
        } else {
            Actionable key = door.getKey();
            if (inventory.containsValue(key)) {
                door.unlock(key);
                this.room = door.getDestination();
                return door.getName() + " " + Messages.getMessage("entered");
            }
            return Messages.getMessage("youCantDoThatMessage");
        }
    }

    public void setRoom(Location room) {
        this.room = room;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
        //Si no juega mas, se desconecta y tiro items al piso.
        if (!playing) {
            for (Actionable actionable : inventory.values()) {
                room.addItem(actionable);
                //removeItem(actionable.getName());

            }
            inventory.clear();
        }
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

}
