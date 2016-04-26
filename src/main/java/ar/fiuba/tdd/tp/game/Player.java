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

    private HashMap<String,Item> inventory;
    private int maxInventory;
    private Room room;

    //TODO: estado (envenado, maldicion)

    public Player(Room room) {
        this.inventory = new HashMap<>();
        this.maxInventory = DEFAULT_MAX_INVENTORY;
        this.room = room;
    }

    public void setMaxInventory(int maxInventory) {
        this.maxInventory = maxInventory;
    }

    public void addItem(Item item) throws MaxInventoryException {
        if (inventory.size() == maxInventory) {
            throw new MaxInventoryException();
        }
        this.inventory.put(item.getName(), item);
    }

    public void removeItem(String name) {
        this.inventory.remove(name);
    }

    public Item getItem(String name) throws ItemNotFoundException {
        Item item = this.inventory.get(name);
        if (item != null) {
            return item;
        }
        throw new ItemNotFoundException();
    }

    public Room getRoom() {
        return this.room;
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

    public Iterator<Item> getInventoryIterator() {
        return this.inventory.values().iterator();
    }

    private boolean checkIdenticalInventory(Iterator<Item> it) {
        while (it.hasNext()) {
            Item item = it.next();
            if (!this.inventory.containsKey(item.getName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkVictory(Player winner) {
        Iterator<Item> it = winner.getInventoryIterator();
        if (winner.getRoom() == this.room && checkIdenticalInventory(it) && this.getInventorySize() == winner.getInventorySize()) {
            return true;
        }
        return false;
    }

    public boolean enter(Door door) {
        System.out.println("Hello!");
        if (!door.isLocked()) {
            this.room = door.getDestination();
        } else {
            Item key = door.getKey();
            System.out.println(showInventory());
            if (inventory.containsValue(key)) {
                door.unlock(key);
                this.room = door.getDestination();
                System.out.println("Success!");
                return true;
            }
            System.out.println("Ey! Where do you go?! Room 2 is locked.");
            return false;
        }
        return true;
    }
}
