package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.game.conditions.Condition;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.random.Util;

import java.util.*;

/**
 * Created by fran on 24/04/16.
 */
public class Room {

    private HashMap<String,ContainerComponent> items;

    private HashMap<String, Door> doors;

    private List<Condition> enterConditions;
    private List<Condition> leaveConditions;

    private String name;

    public Room(String name) {
        this.name = name;
        this.items = new HashMap<>();
        this.doors = new HashMap<>();
        this.enterConditions = new ArrayList<>();
        this.leaveConditions = new ArrayList<>();
    }

    public ContainerComponent getItem(String name) throws ItemNotFoundException {
        ContainerComponent item = this.items.get(name);
        if (item != null) {
            return item;
        }
        throw new ItemNotFoundException();
    }

    public void addContainerComponent( ContainerComponent item) {
        this.items.put(item.getName(), item);
    }

    public void removeItem(String name) {
        this.items.remove(name);
    }


    public Iterator<Map.Entry<String, ContainerComponent>> getItemsIterator() {
        return this.items.entrySet().iterator();
    }

    public Iterator<Map.Entry<String, Door>> getDoorsIterator() {
        return this.doors.entrySet().iterator();
    }

    public Set<String> getItemsNames() {
        return items.keySet();
    }

    public String look() {
        Set<String> items = this.getItemsNames();

        StringBuilder output = new StringBuilder("You are in " + name + "\n");
        output.append("There's a ");
        for (String item : items) {
            output.append(item + " ");
        }
        output.append("in the room.");

        return output.toString();
    }

    public void addDoor(Room destination, Item key) {
        int doorNumber = this.doors.size() + 1;
        StringBuilder doorName = new StringBuilder("door");
        doorName.append(doorNumber);
        Door door;
        if (key == null) {
            door = new Door(destination,doorName.toString());
        } else {
            door = new Door(destination,doorName.toString(),key);
        }
        this.doors.put(String.valueOf(doorNumber), door);
    }

    public Door getDestinationDoor(Room destination) {
        Iterator<HashMap.Entry<String, Door>> it = this.getDoorsIterator();
        while (it.hasNext()) {
            Map.Entry<String, Door> itemEntry = it.next();
            Room destinationAux = itemEntry.getValue().getDestination();
            if (destinationAux == destination ) {
                return itemEntry.getValue();
            }
        }
        return null;
    }

    public HashMap<String, ContainerComponent> getItems() {
        return items;
    }

    public void openContainer(String name, Player player) {
        ContainerComponent component = getItem(name);
        component.openContainer(this, player);
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
