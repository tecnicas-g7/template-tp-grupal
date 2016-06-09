package game.tasks;

import model.Game;

import java.util.TimerTask;

public abstract class ScheduledTask extends TimerTask {

    protected Game game;

    public ScheduledTask(Game game) {
        this.game = game;
    }

}
