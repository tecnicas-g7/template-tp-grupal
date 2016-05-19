package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.utils.Messages;

/*
Created by fran on 27/04/16.
*/

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
        return "Hi! \n The " + item.getName() + " " + Messages.getMessage("hasStolenYourItems");
    }
}
