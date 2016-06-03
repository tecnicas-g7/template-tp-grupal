package game.items;

import game.Location;
import game.items.type.Type;
import game.utils.Messages;

public class Linker extends Actionable {

    private boolean locked;
    private Location destination;
    private Item key;
    private Type type;

    public Linker(Location destination, String name) {
        super(name);
        this.locked = false;
        this.destination = destination;
        type = new Type();
    }

    public Linker(Location destination, String name, Item key) {
        this(destination, name);
        this.locked = true;
        this.key = key;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void lock(Item key) {
        if (key == null || key != this.key) {
            throw new IllegalArgumentException(Messages.getMessage("cantLock") + " " + this.name);
        }
        this.locked = true;
    }

    public void unlock(Item key) {
        if (key == null || key != this.key) {
            throw new IllegalArgumentException(Messages.getMessage("cantUnlock") + " " + this.name);
        }
        this.locked = false;
    }

    public Location getDestination() {
        return destination;
    }

    public Item getKey() {
        return this.key;
    }

    public Type getType() {
        return type;
    }
}
