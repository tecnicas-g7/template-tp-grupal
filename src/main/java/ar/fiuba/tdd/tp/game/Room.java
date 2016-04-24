package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.game.items.Item;

import java.util.HashMap;

/**
 * Created by fran on 24/04/16.
 */
public class Room {

    private HashMap<String,Item> items;

    public Room() {
        this.items = new HashMap<>();
    }

    public Item getItem(String name) {
        return this.items.get(name);
    }

    public void addItem(String name, Item item) {
        this.items.put(name,item);
    }

    public void removeItem(String name) {
        this.items.remove(name);
    }


}
