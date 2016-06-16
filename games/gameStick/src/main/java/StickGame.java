import game.Location;
import game.Player;
import game.actions.ListInventoryAction;
import game.actions.LookAction;
import game.actions.MoveItemAction;
import game.conditions.Condition;
import game.conditions.InventoryCondition;
import game.items.Actionable;
import model.Game;
import model.GameBuilder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by nicol on 18/5/2016.
 */

public class StickGame implements GameBuilder {

    public Game build() {
        Location room = new Location("room");

        Actionable stick = new Actionable("stick");

        room.addItem(stick);

        Player player = new Player(room);
        player.addAction(new LookAction("look"));
        player.addAction(new ListInventoryAction("inventory"));
        stick.addAction(new MoveItemAction(false,true,"pick"));
        Game game = new Game(player);

        game.addRoom(room);

        List<Actionable> items = new ArrayList<>();
        items.add(stick);

        Condition condition = new InventoryCondition(items, true);
        game.addCondition(condition);

        return game;
    }

    @Override
    public String getName() {
        return "stickGame";
    }

    public String getHelp() {
        return "The player will look for an item and find it to win";
    }


}