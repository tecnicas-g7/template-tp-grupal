package ar.fiuba.tdd.tp.game.conditions;

import ar.fiuba.tdd.tp.game.Container;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.StackContainerComponent;

import java.util.List;
import java.util.Stack;

/**
 * Created by fran on 28/04/16.
 */
public class ContainerCondition implements Condition {

    private List<StackContainerComponent> containers;

    public ContainerCondition(List<StackContainerComponent> containers) {
        this.containers = containers;
    }

    @Override
    public boolean isValid(Player player) {
        for (StackContainerComponent container : containers) {
            if (container.isFull()) {
                return true;
            }
        }
        return false;
    }
}
