package ar.fiuba.tdd.tp.model;

import java.util.Hashtable;
import java.util.Iterator;

public class Player extends Describable {

    private Room current;
    private Key passkey;
    private Hashtable<String, Item> elements;

    public Player(String name, Room room) {
        this.name = name;
        this.current = room;
        this.elements = new Hashtable<>();

    }

    private int getInventorySize() {
        return this.elements.size();
    }

    public void pickKey(Key key) {
        this.passkey = key;
    }

    public Iterator<Item> getInventoryIterator() {
        return this.elements.values().iterator();
    }

    /*public Item dropFromInventory(Item item) {
        return this.inventory.remove(item.getName().toLowerCase());
    }

    public Item dropFromInventoryByName(String name) {
        Item item = this.inventory.remove(name.toLowerCase());
        return item;
    }*/

    public void addToInventory(Item item) {
        this.elements.put(item.getName().toLowerCase(), item);
    }

    private boolean checkIdenticalInventory(Iterator<Item> it) {
        while (it.hasNext()) {
            Item item = it.next();
            if (!this.elements.containsKey(item.getName().toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkVictory(Player winner) {
        Iterator<Item> it = winner.getInventoryIterator();
        if (winner.getRoom() == this.current && checkIdenticalInventory(it) && this.getInventorySize() == winner.getInventorySize())
            return true;
        return false;
    }

    /*
    public Item getFromInventoryByName(String name) {
        Item item = this.inventory.get(name.toLowerCase());
        return item;
    }*/

    private Room getRoom() {
        return this.current;
    }

    public void move(Room room, Door door) {
        if (!door.isLocked()) {
            this.current = room;
        } else {
            System.out.println("Ey! Where do you go?! Room 2 is locked.");
        }
    }

    public void openDoor(Door door) {
        door.unlock(passkey);
    }
}
