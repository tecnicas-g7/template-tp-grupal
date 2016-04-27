package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by javier on 4/25/16.
 */
public class SingleItemContainer extends Container {

    private Item item;

    public SingleItemContainer(String name) {
        super(name);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    //When the player opens the container, the item is no longer in the container
    public Item openContainer() {
        Item auxItem = item;
        item = null;
        return auxItem;

    }
}
