package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.*;

import ar.fiuba.tdd.tp.game.actions.CheckAction;
import ar.fiuba.tdd.tp.game.actions.MoveAction;
import ar.fiuba.tdd.tp.game.conditions.ContainerCondition;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.items.StackContainerComponent;

import java.util.ArrayList;
import java.util.List;


/**
 Created by ltessore on 27/04/16.
 */
public class HanoiTower implements GameFactory {

    public Game getGame() {

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

    private static void addConditions(Game game, StackContainerComponent stack2, StackContainerComponent stack3) {

        List<StackContainerComponent> containers = new ArrayList<>();
        containers.add(stack2);
        containers.add(stack3);
        game.addCondition(new ContainerCondition(containers));
    }

    private static void addActionsToStacks(StackContainerComponent stack1, StackContainerComponent stack2, StackContainerComponent stack3) {
        stack1.addAction(new MoveAction());
        stack1.addAction(new CheckAction());
        stack2.addAction(new MoveAction());
        stack2.addAction(new CheckAction());
        stack3.addAction(new MoveAction());
        stack3.addAction(new CheckAction());
    }

    public String getHelp() {
        return "It consists of three rods, and a number of disks of different sizes which can slide onto any rod. ";
    }
}