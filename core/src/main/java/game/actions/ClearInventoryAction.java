package game.actions;



/*
Created by fran on 27/04/16.
*/

import game.Player;
import game.items.Actionable;

public class ClearInventoryAction extends Action {

    public ClearInventoryAction(String name) {
        super(name);
    }

    public ClearInventoryAction(String name, String clearInventoryMessage) {
        super(name);
        this.clearInventoryMessage = clearInventoryMessage;
    }

    private String clearInventoryMessage;


    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        player.clearInventory();
        //return "Hi! \n The " + item.getName() + " " + Messages.getMessage("hasStolenYourItems");
        return getClearInventoryMessage();
    }


    public String getClearInventoryMessage() {
        return clearInventoryMessage;
    }

    public void setClearInventoryMessage(String clearInventoryMessage) {
        this.clearInventoryMessage = clearInventoryMessage;
    }
}
