package ar.fiuba.tdd.tp.game.items;

import ar.fiuba.tdd.tp.game.Describable;
import ar.fiuba.tdd.tp.game.actions.Action;

/**
 * Created by fran on 24/04/16.
 */

public class Item extends Describable {

    public Item(String name) {
        super(name);
    }

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }

    public String getName() {
        return name;
    }


}
