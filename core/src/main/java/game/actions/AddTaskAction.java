package game.actions;


import game.Player;
import game.items.Actionable;
import game.tasks.ScheduledTask;
import model.Game;

import java.time.Clock;
import java.util.Timer;


public class AddTaskAction extends Action {

    private ScheduledTask task;

    public AddTaskAction(String name, ScheduledTask task) {
        super(name);
        this.task = task;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        addTask();
        return null;
    }

    public void addTask() {
        task.addTask();
    }

}
