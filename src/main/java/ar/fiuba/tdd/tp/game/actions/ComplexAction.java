package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;

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
        StringBuilder resultExecute = new StringBuilder();
        actions.forEach((action) -> {
                String result = action.execute(tokens,player,item);
                if ( result.length()>0){
                resultExecute.append(result);
                resultExecute.append(",");
                }
            });

        return resultExecute.substring(0,resultExecute.length()-1).toString();
    }
}
