package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fran on 24/04/16.
 */

public class Character {

    private static final int DEFAULT_MAX_INVENTORY = 10;

    private HashMap<String,Item> inventory;
    private int maxInventory;

    //TODO: estado (envenado, maldicion)

    public Character() {
        this.inventory = new HashMap<>();
        this.maxInventory = DEFAULT_MAX_INVENTORY;
    }

    public void setMaxInventory(int maxInventory) {
        this.maxInventory = maxInventory;
    }

    public void addItem(String name, Item item) throws MaxInventoryException {
        if (inventory.size() == maxInventory) {
            throw new MaxInventoryException();
        }
        this.inventory.put(name,item);
    }

    public void removeItem(String name) {
        this.inventory.remove(name);
    }

    public Item getItem(String name) {
        return this.inventory.get(name);
    }
}
