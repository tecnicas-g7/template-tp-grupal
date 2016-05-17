package ar.fiuba.tdd.tp.game.items;

import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.items.Describable;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.items.type.Type;

public class Linker extends Describable {

    private boolean locked;
    private Location destination;
    private Item key;
    private Type type;

    Linker(Location destination, String name) {
        super(name);
        this.locked = false;
        this.destination = destination;
        type = new Type();
    }

    Linker(Location destination, String name, Item key) {
        this(destination, name);
        this.locked = true;
        this.key = key;
    }

    boolean isLocked() {
        return this.locked;
    }

    public void lock(Item key) {
        if (key == null || key != this.key) {
            throw new IllegalArgumentException("Can't lock " + this.name);
        }
        this.locked = true;
    }

    void unlock(Item key) {
        if (key == null || key != this.key) {
            throw new IllegalArgumentException("Can't unlock " + this.name);
        }
        this.locked = false;
    }

    Location getDestination() {
        return destination;
    }

    Item getKey() {
        return this.key;
    }

    public Type getType() {
        return type;
    }
}
