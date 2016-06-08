package game.items;

import game.Location;
import game.utils.Messages;

public class Linker extends Actionable {

    private boolean locked;
    private Location destination;
    private Actionable key;
//    private Type type;

    public Linker(Location destination, String name) {
        super(name);
        this.locked = false;
        this.destination = destination;
//        type = new Type();
    }

    public Linker(Location destination, String name, Actionable key) {
        this(destination, name);
        this.locked = true;
        this.key = key;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void lock(Actionable key) {
        if (key == null || key != this.key) {
            throw new IllegalArgumentException(Messages.getMessage("cantLock") + " " + this.name);
        }
        this.locked = true;
    }

    public void unlock(Actionable key) {
        if (key == null || key != this.key) {
            throw new IllegalArgumentException(Messages.getMessage("cantUnlock") + " " + this.name);
        }
        this.locked = false;
    }

    public Location getDestination() {
        return destination;
    }

    public Actionable getKey() {
        return this.key;
    }
/*
    public Type getType() {
        return type;
    }
    */
}
