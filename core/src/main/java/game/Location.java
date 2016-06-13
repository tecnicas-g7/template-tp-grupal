package game;


import exceptions.ItemNotFoundException;
import game.actions.Action;
import game.conditions.Condition;
import game.items.Actionable;
import game.items.Linker;
import game.utils.Messages;
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
        Actionable actionable = getLinker(doors,name);
        actionable = (actionable != null) ? actionable : getDescribable(items,name);
        return actionable;
    }

    public void addItem(Actionable item) {
        this.items.put(item.getName(), item);
        item.setContainer(this);
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
        removeDescribable(items,name);
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

    public void addDoor(Location destination, Actionable key, Action action) {
        int doorNumber = this.doors.size() + 1;
        String doorName = "door";
        doorName = doorName.concat(String.valueOf(doorNumber));
        addDoor(destination, key, doorName, action);
    }

    public void addDoor(Location destination, Actionable key, String name, Action action) {
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
        return checkConditions(this.enterConditions, player);
    }

    public boolean validLeaveConditions(Player player) {
        return checkConditions(this.leaveConditions, player);
    }

    public Location getRandomAdjacentLocation() {
        List<Linker> valuesList = new ArrayList<>(doors.values());
        int randomIndex = new Random().nextInt(valuesList.size());
        Linker randomLinker = valuesList.get(randomIndex);
        return randomLinker.getDestination();
    }

    public static Actionable getLinker(HashMap<String, Linker> doors, String name) {
        return doors.get(name);
    }

    public void removeDescribable(HashMap<String, Actionable> items, String name) {
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

    public  Actionable getDescribable(HashMap<String, Actionable> items, String name)
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

    public boolean checkConditions(List<Condition> conditions, Player player) {
        for (Condition condition : conditions) {
            if (condition.isValid(player)) {
                return true;
            }
        }
        if (conditions.size() == 0) {
            return  true;
        } else {
            return false;
        }
    }

}
