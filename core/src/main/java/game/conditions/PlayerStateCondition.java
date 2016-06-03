package game.conditions;

import game.Player;

/**
 Created by fran on 28/04/16.
 */
public class PlayerStateCondition implements Condition {

    private Player.Status status;

    public PlayerStateCondition(Player.Status status) {
        this.status = status;
    }

    @Override
    public boolean isValid(Player player) {
        return (player.getStatus() != status);
    }
}
