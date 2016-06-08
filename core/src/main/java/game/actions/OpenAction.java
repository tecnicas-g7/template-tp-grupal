package game.actions;


import game.Player;
import game.items.Actionable;



/*
  Created by fran on 27/04/16.
 */
public class OpenAction extends Action {

    public OpenAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        if (checkConditions(player)) {
//            return item.openContainer(player);
        }
        return "Couldnt open container";
    }


}
