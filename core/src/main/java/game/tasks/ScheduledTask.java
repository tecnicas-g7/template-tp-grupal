package game.tasks;

import model.Game;


public abstract class ScheduledTask {
    protected Game game;
    protected long period;
    protected long nextExecution;
    protected long startTimeInMillis;
    protected long currentTimeInMillis;
    protected long simulatedTime;

    public ScheduledTask(Game game, long period, long delay) {
        this.game = game;
        this.startTimeInMillis = System.currentTimeMillis();
        this.currentTimeInMillis = System.currentTimeMillis();
        this.nextExecution = startTimeInMillis + (delay > 0 ? delay:period);
        this.period = period;
        this.simulatedTime = 0;
    }

    public abstract void run();

    public void addTask(){
        game.addTask(this);
    }

    public boolean readyToExecute() {
        //currentTimeInMillis = System.currentTimeMillis() + (secondsToAdd*1000);
        return ((currentTimeInMillis + simulatedTime) >= (nextExecution));
    }

    public boolean isPeriodical() {
        return period > 0;
    }

    public void updateNextExecution() {
        nextExecution = System.currentTimeMillis() + period;
    }

    public void simulateMilli(int secondsToAdd) {
        //currentTimeInMillis = System.currentTimeMillis() + (secondsToAdd*1000);
        //currentTimeInMillis += secondsToAdd * 1000;
        simulatedTime += secondsToAdd * 1000;
    }

    public void updateCurrent() {
        this.currentTimeInMillis = System.currentTimeMillis();
    }
}
