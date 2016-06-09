package game.actions;


import game.Player;
import game.items.Actionable;
import game.utils.Messages;


public class OpenCloseContainerAction extends Action {

    private String status;

    public OpenCloseContainerAction(String name, String status) {
        super(name);
        this.status = status;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        if (checkConditions(player)) {
            //return item.openOrCloseContainer(tokens[0]);
            return item.openOrCloseContainer(status);
        }

        return Messages.getMessage("youCantDoThatMessage");
    }


}
