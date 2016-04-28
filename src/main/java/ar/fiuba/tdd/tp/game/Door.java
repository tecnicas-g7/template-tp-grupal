package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.items.type.Type;

public class Door extends Describable {

    private boolean locked;
    private Room destination;
    private Item key;
    private Type type;

    public Door(Room destination, String name) {
        super(name);
        this.locked = false;
        this.destination = destination;
        type = new Type();
    }

    public Door(Room destination, String name, Item key) {
        this(destination, name);
        this.locked = true;
        this.key = key;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void lock(Item key) {
        if (key == null || key != this.key) {
            throw new IllegalArgumentException("Can't lock " + this.name);
        }
        this.locked = true;
    }

    public void unlock(Item key) {
        if (key == null || key != this.key) {
            throw new IllegalArgumentException("Can't unlock " + this.name);
        }
        this.locked = false;
    }

    public Room getDestination() {
        return destination;
    }

    public Item getKey() {
        return this.key;
    }

    public Type getType() {
        return type;
    }
}
