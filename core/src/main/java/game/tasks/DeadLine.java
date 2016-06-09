package game.tasks;

import model.Game;

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
            //game.getPlayer().changeStatus(new Status("dead"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
