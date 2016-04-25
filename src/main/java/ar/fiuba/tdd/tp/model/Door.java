package ar.fiuba.tdd.tp.model;

public class Door extends Describable {
    private boolean locked;
    private Room destination;

    public boolean isLocked() {
        return this.locked;
    }

    public void lock() {
        this.locked = true;
    }

    public void unlock(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Ey! Where do you go?! Room 2 is locked.");
        }
        this.locked = false;
    }

    public Door(Room destination, String name) {
        this.locked = true;
        this.destination = destination;
        this.name = name;
    }

    public Room getDestination() {
        return destination;
    }
}
