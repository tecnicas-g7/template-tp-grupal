package game.conditions;

import game.Player;

/**
 Created by javier on 6/9/16.
 */
public class isPlayerCondition implements Condition {

    Player player;

    public isPlayerCondition(Player player) {
        this.player = player;
    }

    @Override
    public boolean isValid(Player player) {
        return player.equals(this.player);
    }
}
