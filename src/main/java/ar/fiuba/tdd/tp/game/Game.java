package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.WrongItemActionException;
import ar.fiuba.tdd.tp.game.conditions.Condition;
import ar.fiuba.tdd.tp.game.items.Item;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by fran on 24/04/16.
 */
public class Game {

    private List<Room> rooms;
    private Player player;
    private List<Condition> conditions;

    public Game(Player player) {
        this.rooms = new ArrayList<Room>();
        this.player = player;
        this.conditions = new ArrayList<>();
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    //Metodos creados para q findbugs no joda

    public Player getPlayer() {
        return this.player;
    }

    public String executeActionOnItem(String[] tokens) throws WrongItemActionException {
        String objectName;
        try {
            objectName = tokens[1];
        } catch (Exception e) {
            return "You have to select an item!";
        }
        try {
            Item item = findItem(objectName);
            if (item != null) {
                return item.executeAction(tokens,this.player);
            }
        } catch (ItemNotFoundException ine) {
            return ine.getMessage();
        }
        return null;
    }

    public Item findItem(String objectName) throws ItemNotFoundException {
        try {
            return this.player.getItem(objectName);
        } catch (ItemNotFoundException e) {

            System.out.println(this.player.getRoom().getItem(objectName));
            return this.player.getRoom().getItem(objectName);
        }
    }

    public String look() {
        return this.player.getRoom().look();

    }

    public String showInventory() {
        return this.player.showInventory();
    }

    public void addCondition(Condition condition) {
        this.conditions.add(condition);
    }

    public boolean verifyVictory() {

        for (Condition condition : this.conditions) {
            if (!condition.isValid(this.player)) {
                return false;
            }
        }
        return  true;
    }
}