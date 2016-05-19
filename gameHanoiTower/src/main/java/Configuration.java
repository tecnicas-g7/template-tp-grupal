import game.Location;
import game.Player;
//import game.actions.CheckAction;
import game.actions.MoveAction;
import game.conditions.ContainerCondition;
import game.items.Item;
import game.items.StackContainerComponent;
import model.Game;
import model.GameBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicol on 18/5/2016.
 */
public class Configuration implements GameBuilder {

    public Game build() {

        //Creo 3 stacks
        StackContainerComponent stack1 = new StackContainerComponent("stack1",3);
        StackContainerComponent stack2 = new StackContainerComponent("stack2",3);
        StackContainerComponent stack3 = new StackContainerComponent("stack3",3);
        addActionsToStacks(stack1,stack2,stack3);

        //Creo piezas
        Item pieza1 = new Item("1");
        Item pieza2 = new Item("2");
        Item pieza3 = new Item("3");

        //agrego piezas a un stack (de mayor a menor)
        stack1.addComponent(pieza3);
        stack1.addComponent(pieza2);
        stack1.addComponent(pieza1);

        //agrego las 3 pilas al room
        Location room1 = new Location("R1");
        room1.addItem(stack1);
        room1.addItem(stack2);
        room1.addItem(stack3);

        Player player = new Player(room1);
        player.setMaxInventory(1);
        Game game = new Game(player);

        addConditions(game, stack2, stack3);

        return game;
    }

    @Override
    public String getName() {
        return "hanoiTower";
    }

    private static void addConditions(Game game, StackContainerComponent stack2, StackContainerComponent stack3) {

        List<StackContainerComponent> containers = new ArrayList<StackContainerComponent>();
        containers.add(stack2);
        containers.add(stack3);
        game.addCondition(new ContainerCondition(containers));
    }

    private static void addActionsToStacks(StackContainerComponent stack1, StackContainerComponent stack2, StackContainerComponent stack3) {
        stack1.addAction(new MoveAction("move"));
        //stack1.addAction(new CheckAction("check"));
        stack2.addAction(new MoveAction("move"));
        //stack2.addAction(new CheckAction("check"));
        stack3.addAction(new MoveAction("move"));
        //stack3.addAction(new CheckAction("check"));
    }

    public String getHelp() {
        return "It consists of three rods, and a number of disks of different sizes which can slide onto any rod. ";
    }

}
