package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by fran on 24/04/16.
 */
public class Room extends Describable {

    private HashMap<String,Item> items;

    private HashMap<String, Door> doors;

    private HashMap<String,SingleItemContainer> simplecontainers;

    private HashMap<String,MultipleItemsContainer> multiplecontainers;

    public Room(String name) {
        this.items = new HashMap<>();
        this.doors = new HashMap<>();
        this.simplecontainers = new HashMap<>();
        this.multiplecontainers = new HashMap<>();
        this.name = name;
    }

    public Item getItem(String name) throws ItemNotFoundException {
        Item item = this.items.get(name);
        if (item != null) {
            return item;
        }
        throw new ItemNotFoundException();
    }

    public void addItem( Item item) {
        this.items.put(item.getName(), item);
    }

    public void removeItem(String name) {
        this.items.remove(name);
    }


    public Iterator<Map.Entry<String, Item>> getItemsIterator() {
        return this.items.entrySet().iterator();
    }

    public Iterator<Map.Entry<String, Door>> getDoorsIterator() {
        return this.doors.entrySet().iterator();
    }

    public Set<String> getItemsNames() {
        return items.keySet();
    }

    public String look() {
        Set<String> items = this.getItemsNames();

        StringBuilder output = new StringBuilder("You are in " + this.getName() + "\n");
        output.append("There's a ");
        for (String item : items) {
            output.append(item + " ");
        }
        output.append("in the room.");

        return output.toString();
    }

    public void addDoor(Room destination, Item key) {
        int doorNumber = this.doors.size() + 1;
        Door door;
        if (key == null) {
            door = new Door(destination,String.valueOf(doorNumber));
        } else {
            door = new Door(destination,String.valueOf(doorNumber),key);
        }
        this.doors.put(String.valueOf(doorNumber), door);
    }

    public Door getDestinationDoor(Room destination) {
        Iterator<HashMap.Entry<String, Door>> it = this.getDoorsIterator();
        while (it.hasNext()) {
            Map.Entry<String, Door> itemEntry = it.next();
            Room destinationAux = itemEntry.getValue().getDestination();
            if (destinationAux == destination ) {
                return itemEntry.getValue();
            }
        }
        return null;
    }

    public void addSimpleContainer(SingleItemContainer container) {
        simplecontainers.put(container.getName(),container);
    }

    public void addMultipleContainer(MultipleItemsContainer container) {
        multiplecontainers.put(container.getName(),container);
    }

}
