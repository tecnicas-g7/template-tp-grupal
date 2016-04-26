package ar.fiuba.tdd.tp.game.items;

import java.util.HashMap;

/**
 * Created by fran on 26/04/16.
 */
public class Container extends Item {

    private HashMap<String, Item> items;

    private static final int DEFAULT_MAX_ITEMS = 10;
    private int maxItems;

    public Container(String name, Size size) {
        super(name);
        this.size = size;
        this.items = new HashMap<>();
        this.maxItems = DEFAULT_MAX_ITEMS;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public void addItem(Item item) {
        if (item.getSize().ordinal() < this.size.ordinal() && this.items.size() < maxItems) {
            this.items.put(item.getName(),item);
        }
    }

    public HashMap<String, Item> open() {
        HashMap<String, Item> auxItems = items;
        items = null;
        return auxItems;
    }

}
