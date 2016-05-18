package game;

import exceptions.ItemNotFoundException;
import exceptions.MaxInventoryException;
import game.items.Actionable;
import game.items.Item;
import game.items.Linker;
import game.utils.Messages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*
Created by fran on 24/04/16.
*/

public class Player implements HasItems {

    private static final int DEFAULT_MAX_INVENTORY = 10;

    private HashMap<String,Actionable> inventory;
    private int maxInventory;
    private Location room;

    private Status status;

    public String openContainer(String name) {
        Actionable component = room.getItem(name);
        return component.openContainer(this);
    }

    public void clearInventory() {
        inventory.clear();
    }

    public enum Status {
        alive, poisoned
    }

    public Player(Location room) {
        this.inventory = new HashMap<>();
        this.maxInventory = DEFAULT_MAX_INVENTORY;
        this.room = room;
        this.status = Status.alive;
    }

    public void setMaxInventory(int maxInventory) {
        this.maxInventory = maxInventory;
    }

    public void changeStatus(Status newStatus) {
        this.status = newStatus;
    }

    public void addItem(Actionable item) throws MaxInventoryException {
        if (inventory.size() == maxInventory) {
            throw new MaxInventoryException();
        }
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

    public Status getStatus() {
        return this.status;
    }

    String showInventory() {
        Set<String> items = this.inventory.keySet();
        String inventoryNames = "";
        for (String item : items) {
            inventoryNames = inventoryNames.concat(item + " ");
        }
        String output = "You have ";
        output = output.concat(inventoryNames);
        return output.toString();
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
            Item key = door.getKey();
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

}
