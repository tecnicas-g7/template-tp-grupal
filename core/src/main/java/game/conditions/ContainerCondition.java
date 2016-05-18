package game.conditions;

import game.Player;
import game.items.StackContainerComponent;

import java.util.List;

/**
  Created by fran on 28/04/16.
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
