package ar.fiuba.tdd.tp.game.random;

import ar.fiuba.tdd.tp.game.items.Item;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fran on 26/04/16.
 */
public class Util {

    public static boolean itemsInInventory(List<Item> list, HashMap<String,Item> inventory) {
        for (Item item : list) {
            if (!inventory.containsValue(item)) {
                return false;
            }
        }
        return true;
    }

}
