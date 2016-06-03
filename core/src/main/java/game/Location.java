package game;


import exceptions.ItemNotFoundException;
import game.actions.Action;
import game.conditions.Condition;
import game.items.Actionable;
import game.items.Item;
import game.items.Linker;
import game.utils.Messages;
import game.utils.Util;

import java.util.*;

/*
Created by fran on 24/04/16.
*/

public class Location implements HasItems {

    private HashMap<String,Actionable> items;

    private HashMap<String, Linker> doors;

    private List<Condition> enterConditions;
    private List<Condition> leaveConditions;

    private String name;

    public Location(String name) {
        this.name = name;
        this.items = new HashMap<>();
        this.doors = new HashMap<>();
        this.enterConditions = new ArrayList<>();
        this.leaveConditions = new ArrayList<>();
    }

    public Actionable getItem(String name) throws ItemNotFoundException {
        Actionable actionable = Util.getLinker(doors,name);
        actionable = (actionable != null) ? actionable : Util.getDescribable(items,name);
        return actionable;
    }

    public void addItem(Actionable item) {
        this.items.put(item.getName(), item);
    }

    @Override
    public int getInventorySize() {
        return this.items.size();
    }

    @Override
    public HashMap<String, Actionable> getInventory() {
        return items;
    }

    public void removeItem(String name) {
        Util.removeDescribable(items,name);
    }

    public Iterator<Map.Entry<String, Linker>> getDoorsIterator() {
        return this.doors.entrySet().iterator();
    }


    public String look() {
        StringBuilder output = new StringBuilder(Messages.getMessage("youAreIn") + " " + name + " ");
        output.append(Messages.getMessage("youCanSeeA") + " ");
        items.forEach((key,value) -> {
                output.append(value.look());
                output.append(" ");
            }
        );
        doors.forEach((key, value) -> output.append(value.getName().concat(" ")));
        output.append(" in ");
        output.append(name);

        return output.toString();
    }

    public void addDoor(Location destination, Item key, Action action) {
        int doorNumber = this.doors.size() + 1;
        String doorName = "door";
        doorName = doorName.concat(String.valueOf(doorNumber));
        addDoor(destination, key, doorName, action);
    }

    public void addDoor(Location destination, Item key, String name, Action action) {
        Linker door;
        if (key == null) {
            door = new Linker(destination, name);
        } else {
            door = new Linker(destination, name, key);
        }
        door.addAction(action);
        this.doors.put(door.getName(), door);
    }

    public Linker getDestinationDoor(Location destination) {
        Iterator<HashMap.Entry<String, Linker>> it = this.getDoorsIterator();
        while (it.hasNext()) {
            Map.Entry<String, Linker> itemEntry = it.next();
            Location destinationAux = itemEntry.getValue().getDestination();
            if (destinationAux == destination ) {
                return itemEntry.getValue();
            }
        }
        return null;
    }

    public HashMap<String, Actionable> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public void addEnterCondition(Condition condition) {
        this.enterConditions.add(condition);
    }

    public void addLeaveCondition(Condition condition) {
        this.leaveConditions.add(condition);
    }

    public boolean validEnterConditions(Player player) {
        return Util.checkConditions(this.enterConditions, player);
    }

    public boolean validLeaveConditions(Player player) {
        return Util.checkConditions(this.leaveConditions, player);
    }

}
