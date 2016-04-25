package ar.fiuba.tdd.tp.model;

public class Item extends Describable {

    public Item(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("item must have a name");
        }
        this.name = name;
    }
}
