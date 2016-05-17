package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.items.Describable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by fran on 17/05/16.
 */
public interface HasItems {

    void addItem(Describable item) throws MaxInventoryException;

    default void clearInventory() {

    }

    int getInventorySize();

    HashMap<String,Describable> getInventory();

    Describable getItem(String name) throws ItemNotFoundException;

    void removeItem(String name);
}
