package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by fran on 24/04/16.
 */
public class Room {

    private HashMap<String,Item> items;

    public Room() {
        this.items = new HashMap<>();
    }

    public Item getItem(String name) throws ItemNotFoundException {
        Item item = this.items.get(name);
        if (item != null) {
            return item;
        }
        throw new ItemNotFoundException();
    }

    public void addItem( Item item) {
        this.items.put(item.getName(),item);
    }

    public void removeItem(String name) {
        this.items.remove(name);
    }


    public Set<String> getItemsNames() {
        return items.keySet();
    }

    public String look() {
        Set<String> items = this.getItemsNames();
        StringBuilder output = new StringBuilder();
        output.append("There's a");
        for (String item : items) {
            output.append(item + " ");
        }
        output.append("in the room.");
        return output.toString();
    }
}
