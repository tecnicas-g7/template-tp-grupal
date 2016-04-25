package ar.fiuba.tdd.tp.model;

import java.util.*;

public class Room extends Describable {
    private static final int DEFAULT_ITEMS_SIZE = 11;
    private static final int DEFAULT_DOORS_SIZE = 4;

    private Hashtable<String, Item> items;

    private Hashtable<String, Door> doors;

    public void addItem(Item item) {
        this.items.put(item.getName().toLowerCase(), item);
    }

    public int getNumberOfItems() {
        return this.items.size();
    }

    public boolean hasNoItems() {
        return this.items.isEmpty();
    }

    public Collection<Item> getItems() {
        return this.items.values();
    }

    public Iterator<Map.Entry<String, Item>> getItemsIterator() {
        return this.items.entrySet().iterator();
    }

    public Iterator<Map.Entry<String, Door>> getDoorsIterator() {
        return this.doors.entrySet().iterator();
    }

    public StringBuffer getListOfItems() {
        Iterator<Map.Entry<String, Item>> it = this.getItemsIterator();
        StringBuffer thereis = new StringBuffer("There is ");
        while (it.hasNext()) {
            Map.Entry<String, Item> itemEntry = it.next();
            thereis.append("a " + itemEntry.getKey() + " ");
        }
        return thereis;
    }

   /* public Item removeItemByName(String name) {
        Item item = this.items.remove(name.toLowerCase());
        return item;
    }

    public Item getItemByName(String name) {
        Item item = this.items.get(name.toLowerCase());
        return item;
    }*/


    public void addDoor(Room destination) {
        int doorNumber = this.doors.size() + 1;
        Door door = new Door(destination,String.valueOf(doorNumber));
        this.doors.put(String.valueOf(doorNumber),door);
    }


    public Room(String name) {
        this.name = name;
        this.items = new Hashtable<String, Item>(Room.DEFAULT_ITEMS_SIZE);
        this.doors = new Hashtable<String, Door>(Room.DEFAULT_DOORS_SIZE);
    }

    public Door getDestinationDoor(Room destination) {
        Iterator<Map.Entry<String, Door>> it = this.getDoorsIterator();
        while (it.hasNext()) {
            Map.Entry<String, Door> itemEntry = it.next();
            Room destinationAux = itemEntry.getValue().getDestination();
            if (destinationAux == destination ) {
                return itemEntry.getValue();
            }
        }
        return null;
    }
    
    public String toString() {
        return this.getName();
    }
}
