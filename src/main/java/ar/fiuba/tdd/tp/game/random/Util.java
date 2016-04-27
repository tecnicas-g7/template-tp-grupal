package ar.fiuba.tdd.tp.game.random;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.conditions.Condition;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fran on 26/04/16.
 */
public class Util {

    public static boolean itemsInInventory(List<ContainerComponent> list, HashMap<String,ContainerComponent> inventory) {
        for (ContainerComponent item : list) {
            if (!inventory.containsValue(item)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkConditions(List<Condition> conditions, Player player) {
        for (Condition condition : conditions) {
            if (!condition.isValid(player)) {
                return false;
            }
        }
        return  true;
    }

}
