package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

    public void addItem(String name, Item item) throws MaxInventoryException {
        if (inventory.size() == maxInventory) {
            throw new MaxInventoryException();
        }
        this.inventory.put(name, item);
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
}
