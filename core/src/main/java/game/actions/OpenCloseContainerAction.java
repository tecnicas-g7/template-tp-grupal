package game.actions;


import game.Player;
import game.items.Actionable;
import game.utils.Messages;


public class OpenCloseContainerAction extends Action {

    public OpenCloseContainerAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        if (checkConditions(player)) {
            return item.openOrCloseContainer(tokens[0]);
        }

        return Messages.getMessage("youCantDoThatMessage");
    }


}
