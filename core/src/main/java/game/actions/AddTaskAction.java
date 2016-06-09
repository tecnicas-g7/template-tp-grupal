package game.actions;


import game.Player;
import game.items.Actionable;
import game.tasks.ScheduledTask;
import model.Game;

import java.util.Timer;


public class AddTaskAction extends Action {

    private ScheduledTask task;
    private int period;
    private int delay;

    public AddTaskAction(String name, ScheduledTask task,int delay, int period) {
        super(name);
        this.task = task;
        this.delay = delay;
        this.period = period;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        addTask();
        return null;
    }

    public void addTask() {
        Timer timer = new Timer();
        if (period == 0) {
            timer.schedule(task,delay);
            return;
        }
        timer.scheduleAtFixedRate(task, delay, period);
        //game.addTask(task,delay,period);
    }

}
