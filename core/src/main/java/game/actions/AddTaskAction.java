package game.actions;


import game.Player;
import game.items.Actionable;
import game.tasks.ScheduledTask;

import java.util.Timer;


public class AddTaskAction extends Action {

    private ScheduledTask task;
    private int period;
    private int delay;
    private Actionable specificItem;

    public AddTaskAction(String name, ScheduledTask task,int delay, int period, Actionable specificItem) {
        super(name);
        this.task = task;
        this.delay = delay;
        this.period = period;
        this.specificItem = specificItem;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        if (item.equals(specificItem)) {
            addTask();
        }
        return "";
    }

    public void addTask() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, delay, period);
    }

}
