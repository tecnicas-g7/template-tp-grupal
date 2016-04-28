package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.*;

/**
 * Created by fran on 24/04/16.
 */

public class Player {

    private static final int DEFAULT_MAX_INVENTORY = 10;

    private HashMap<String,ContainerComponent> inventory;
    private int maxInventory;
    private Room room;

    //TODO: estado (envenado)
    private Status status;

    public void openRoomContainer(String name) {
        room.openContainer(name, this);
    }

    public void clearInventory() {
        inventory = new HashMap<>();
    }

    public enum Status {
        alive, poisoned
    }

    public Player(Room room) {
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

    public void addItem(ContainerComponent item) throws MaxInventoryException {
        if (inventory.size() == maxInventory) {
            throw new MaxInventoryException();
        }
        this.inventory.put(item.getName(), item);
    }

    public void removeItem(String name) {
        this.inventory.remove(name);
    }

    public ContainerComponent getItem(String name) throws ItemNotFoundException {
        ContainerComponent item = this.inventory.get(name);
        if (item != null) {
            return item;
        }
        throw new ItemNotFoundException();
    }

    public Room getRoom() {
        return this.room;
    }

    public Status getStatus() {
        return this.status;
    }

    public String showInventory() {
        Set<String> items = this.inventory.keySet();
        StringBuilder inventoryNames = new StringBuilder();
        for (String item : items) {
            inventoryNames.append(item + " ");
        }
        StringBuilder output = new StringBuilder();
        output.append("You have");
        output.append(inventoryNames.toString());
        return output.toString();
    }

    private int getInventorySize() {
        return this.inventory.size();
    }

    public HashMap<String,ContainerComponent> getInventory() {
        return this.inventory;
    }

    public Iterator<ContainerComponent> getInventoryIterator() {
        return this.inventory.values().iterator();
    }

    private boolean checkIdenticalInventory(Iterator<ContainerComponent> it) {
        while (it.hasNext()) {
            ContainerComponent item = it.next();
            if (!this.inventory.containsKey(item.getName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkVictory(Player winner) {
        Iterator<ContainerComponent> it = winner.getInventoryIterator();
        if (winner.getRoom() == this.room && checkIdenticalInventory(it) && this.getInventorySize() == winner.getInventorySize()) {
            return true;
        }
        return false;
    }

    public boolean enter(Door door) {
        if (!door.isLocked()) {
            this.room = door.getDestination();
        } else {
            Item key = door.getKey();
            if (inventory.containsValue(key)) {
                door.unlock(key);
                this.room = door.getDestination();
                return true;
            }
            System.out.println("Ey! Where do you go?! Room 2 is locked.");
            return false;
        }
        return true;
    }


}
