package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.items.Actionable;

import java.util.HashMap;

/**
 * Created by fran on 17/05/16.
 */
public interface HasItems {

    void addItem(Actionable item) throws MaxInventoryException;

    default void clearInventory() {

    }

    int getInventorySize();

    HashMap<String,Actionable> getInventory();

    Actionable getItem(String name) throws ItemNotFoundException;

    void removeItem(String name);
}
