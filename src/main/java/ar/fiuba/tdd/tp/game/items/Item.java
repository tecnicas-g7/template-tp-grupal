package ar.fiuba.tdd.tp.game.items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fran on 24/04/16.
 */
public abstract class Item {

    private List<String> actions;

    public Item() {
        this.actions = new ArrayList<>();
    }

    public void addAction(String action) {
        this.actions.add(action);
    }

    //TODO: Le llegaria Escenario como parametro o de otra forma?
    public void execute(String action) {

    }
}
