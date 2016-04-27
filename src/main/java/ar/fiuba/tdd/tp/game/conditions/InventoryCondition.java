package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.random.Util;

import java.util.List;

/**
 * Created by fran on 25/04/16.
 */
public class InventoryCondition implements Condition {

    List<ContainerComponent> inventory;

    public InventoryCondition(List<ContainerComponent> items) {
        this.inventory = items;
    }

    @Override
    public boolean isValid(Player player) {
        return Util.itemsInInventory(inventory,player.getInventory());
    }
}
