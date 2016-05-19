package game.utils;

/*
Created by fran on 26/04/16.
*/

import exceptions.ItemNotFoundException;
import game.Player;
import game.conditions.Condition;
import game.items.Actionable;
import game.items.Linker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Util {

    public static boolean itemsInInventory(List<Actionable> list, HashMap<String,Actionable> inventory) {
        for (Actionable item : list) {
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

   /* public static ContainerComponent getContainerComponent(HashMap<String,ContainerComponent> components, String name)
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

    public static void removeComponent(HashMap<String,ContainerComponent> items, String name) {
        if (items.get(name) != null) {
            items.remove(name);
        } else {
            Iterator<Map.Entry<String, ContainerComponent>> iterator = items.entrySet().iterator();
            while (iterator.hasNext()) {
                ContainerComponent container = iterator.next().getValue();
                ContainerComponent item = container.getChild(name);
                if (item != null) {
                    container.removeComponent(item);
                    return;
                }
            }
        }
    }*/

    public static Actionable getDescribable(HashMap<String, Actionable> items, String name)
        throws ItemNotFoundException {
        Actionable item = items.get(name);
        if (item != null) {
            return item;
        }
        Iterator<Map.Entry<String, Actionable>> iterator = items.entrySet().iterator();
        while (iterator.hasNext()) {
            Actionable container = iterator.next().getValue();
            item = container.getChild(name);
            if (item != null) {
                return item;
            }
        }
        throw new ItemNotFoundException();
    }

    public static void removeDescribable(HashMap<String, Actionable> items, String name) {
        if (items.get(name) != null) {
            items.remove(name);
        } else {
            Iterator<Map.Entry<String, Actionable>> iterator = items.entrySet().iterator();
            while (iterator.hasNext()) {
                Actionable container = iterator.next().getValue();
                Actionable item = container.getChild(name);
                if (item != null) {
                    container.removeComponent(item);
                    return;
                }
            }
        }
    }

    public static Actionable getLinker(HashMap<String, Linker> doors, String name) {
        Linker item = doors.get(name);
        if (item != null) {
            return item;
        }
        return null;
    }

}
