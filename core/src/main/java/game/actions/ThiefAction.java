package game.actions;


/*
Created by fran on 27/04/16.
*/

import game.Player;
import game.items.Actionable;

public class ThiefAction extends Action {

   /* @Override
    public String getName() {
        return "talk";
    }
*/
    public ThiefAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        player.clearInventory();
        return "Hi! \n The " + item.getName() + " has stolen your items!";
    }
}
