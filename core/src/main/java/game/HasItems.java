package game;

import exceptions.ItemNotFoundException;
import exceptions.MaxInventoryException;
import game.items.Actionable;

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
