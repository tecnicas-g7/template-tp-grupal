package game.actions;



/*
Created by fran on 27/04/16.
*/

import game.Player;
import game.items.Actionable;
import game.utils.Messages;

public class ThiefAction extends Action {

    public ThiefAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        player.clearInventory();
        return "Hi! \n The " + item.getName() + " " + Messages.getMessage("hasStolenYourItems");
    }
}
