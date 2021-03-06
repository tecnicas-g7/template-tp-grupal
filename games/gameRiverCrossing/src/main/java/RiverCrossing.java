import game.Location;
import game.Player;
import game.actions.EnterAction;
import game.actions.ListInventoryAction;
import game.actions.LookAction;
import game.actions.MoveItemAction;
import conditions.ItemTypeCondition;
import game.conditions.RoomWithItemsCondition;
import game.items.Actionable;
import items.ActionableType;
import items.type.CarnivorousType;
import items.type.HerbivorousType;
import items.type.PlantType;
import model.Game;
import model.GameBuilder;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicol on 18/5/2016.
 */public class RiverCrossing implements GameBuilder {

    public Game build() {

        Location leftShore = new Location("south-shore");
        Location rightShore = new Location("north-shore");
        EnterAction crossAction = new EnterAction("cross");
        leftShore.addDoor(rightShore, null, "north-shore",crossAction);
        rightShore.addDoor(leftShore, null, "south-shore",crossAction);

        leftShore.addEnterCondition(new ItemTypeCondition());
        leftShore.addLeaveCondition(new ItemTypeCondition());

        rightShore.addEnterCondition(new ItemTypeCondition());
        rightShore.addLeaveCondition(new ItemTypeCondition());

        Player player = new Player(leftShore);
        player.addAction(new LookAction("look"));
        player.addAction(new ListInventoryAction("inventory"));
        player.setMaxInventory(1);

        createComponents(player).forEach(item -> leftShore.addItem(item));

        Game game = new Game(player);

        game.addRoom(leftShore);
        game.addRoom(rightShore);

        game.addCondition(setWinCondition(rightShore,player));

        return game;
    }

    @Override
    public String getName() {
        return "riverCrossing";
    }

    private static List<Actionable> createComponents(Player player) {

        ActionableType sheep = new ActionableType("sheep");
        sheep.setType(new HerbivorousType());
        sheep = addActions(sheep,player);

        ActionableType wolf = new ActionableType("wolf");
        wolf.setType(new CarnivorousType());
        wolf = addActions(wolf,player);

        ActionableType cabbage = new ActionableType("cabbage");
        cabbage.setType(new PlantType());
        cabbage = addActions(cabbage,player);

        List<Actionable> items = new ArrayList<>();
        items.add(sheep);
        items.add(wolf);
        items.add(cabbage);

        return items;
    }

    private static ActionableType addActions(ActionableType item, Player player) {
        MoveItemAction take = new MoveItemAction(false,true,"take");
        MoveItemAction leave = new MoveItemAction(true,false,"leave");
        item.addAction(take);
        item.addAction(leave);
        return item;
    }

    private static RoomWithItemsCondition setWinCondition(Location room, Player player) {
        return new RoomWithItemsCondition(createComponents(player), room);
    }

    public String getHelp() {
        return "Your goal is to bring the wolf, the sheep, and the head of cabbage to the left side of the river in the man's boat.";
    }
}