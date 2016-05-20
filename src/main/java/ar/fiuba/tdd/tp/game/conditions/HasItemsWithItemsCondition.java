package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.HasItems;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.utils.Util;

import java.util.List;

/**
 * Created by fran on 20/05/16.
 */
public class HasItemsWithItemsCondition implements Condition {

    private List<Actionable> inventory;
    private HasItems hasItems;
    private boolean valid;


    @Override
    public boolean isValid(Player player) {
        boolean valid = Util.itemsInInventory(inventory, hasItems.getInventory());
        return this.valid && valid || !this.valid && !valid;
    }

    public HasItemsWithItemsCondition(List<Actionable> items, HasItems hasItems, boolean valid) {
        this.inventory = items;
        this.hasItems = hasItems;
        this.valid = valid;
    }

}
