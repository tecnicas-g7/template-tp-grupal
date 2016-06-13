package game.actions;


import game.Player;
import game.items.Actionable;

import java.util.ArrayList;
import java.util.List;

/**
 Created by fran on 17/05/16.
 */
public class ComplexAction extends Action {

    private List<Action> actions;

    public ComplexAction(String name) {
        super(name);
        this.actions = new ArrayList<>();
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        StringBuilder result = new StringBuilder();
        for (Action action : actions) {
            String actionResult = action.execute(tokens,player,item);
            if (actionResult != null) {
                result.append(actionResult + '\n');
            }
        }
        return result.toString();
    }
}
