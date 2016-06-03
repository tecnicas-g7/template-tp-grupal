package game.actions;


import game.Location;
import game.Player;
import game.items.Actionable;

public class LookAction extends Action {

    public LookAction(String name) {
        super(name);
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        Location location = player.getRoom();
        return location.look();
    }

}
