package game.tasks;

import model.Game;


public abstract class ScheduledTask {
    protected Game game;
    protected long period;
    protected long nextExecution;
    protected long startTimeInMillis;
    protected long currentTimeInMillis;

    public ScheduledTask(Game game, long period, long delay) {
        this.game = game;
        this.startTimeInMillis = System.currentTimeMillis();
        this.currentTimeInMillis = System.currentTimeMillis();
        this.nextExecution = startTimeInMillis + (delay > 0 ? delay:period);
        this.period = period;
    }

    public abstract void run();

    public void addTask(){
        game.addTask(this);
    }

    public boolean readyToExecute() {
        return (currentTimeInMillis >= (nextExecution));
    }

    public boolean isPeriodical() {
        return period > 0;
    }

    public void updateNextExecution() {
        nextExecution = System.currentTimeMillis() + period;
    }

    public void simulateMilli(int secondsToAdd) {
        currentTimeInMillis = System.currentTimeMillis() + (secondsToAdd*1000);
    }
}
