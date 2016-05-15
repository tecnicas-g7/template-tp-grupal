package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.*;

/*
Created by fran on 24/04/16.
*/

public class Player {

    private static final int DEFAULT_MAX_INVENTORY = 10;

    private HashMap<String,ContainerComponent> inventory;
    private int maxInventory;
    private Location room;

    private Status status;

    //Se cambia por el metodo de abajo
    /*public void openRoomContainer(String name) {
        room.openContainer(name);
    }*/

    public String openContainer(String name) {
        ContainerComponent component = room.getItem(name);
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

    private int getInventorySize() {
        return this.inventory.size();
    }

    public HashMap<String,ContainerComponent> getInventory() {
        return this.inventory;
    }

    private Iterator<ContainerComponent> getInventoryIterator() {
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
        return (winner.getRoom() == this.room && checkIdenticalInventory(it) && this.getInventorySize() == winner.getInventorySize());
    }

    public String enter(Linker door) {
        if (!door.isLocked()) {
            this.room = door.getDestination();
            return door.getName() +  " entered!";
        } else {
            Item key = door.getKey();
            if (inventory.containsValue(key)) {
                door.unlock(key);
                this.room = door.getDestination();
                return door.getName() + " entered!";
            }
            return "You can't do that!";
        }
    }

    public boolean cross(Location room) {
        this.room = room;
        System.out.println("Crossed!");
        return true;
    }

    public void setRoom(Location room) {
        this.room = room;
    }

}
