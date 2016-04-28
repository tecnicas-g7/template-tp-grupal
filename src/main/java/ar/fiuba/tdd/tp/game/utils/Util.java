package ar.fiuba.tdd.tp.game.utils;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.conditions.Condition;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public static ContainerComponent getContainerComponent(HashMap<String,ContainerComponent> components, String name)
            throws ItemNotFoundException {
        ContainerComponent item = components.get(name);
        if (item != null) {
            return item;
        }
        Iterator<Map.Entry<String, ContainerComponent>> iterator = components.entrySet().iterator();
        while (iterator.hasNext()) {
            ContainerComponent container = iterator.next().getValue();
            item = container.getChild(name);
            if (item != null) {
                return item;
            }
        }
        throw new ItemNotFoundException();
    }

}
