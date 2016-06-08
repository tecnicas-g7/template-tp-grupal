package game.tasks;

import model.Game;
import game.states.StatePlayer;

/**
 Created by javier on 6/6/16.
 */
public class DeadLine extends ScheduledTask {
    Game game;

    public  DeadLine(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        try {
            game.getPlayer().changeStatus(new StatePlayer("dead"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
