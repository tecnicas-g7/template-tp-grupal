package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.*;

import ar.fiuba.tdd.tp.game.actions.CheckAction;
import ar.fiuba.tdd.tp.game.actions.MoveAction;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.Iterator;


/**
 * Created by ltessore on 27/04/16.
 */
public class HanoiTower {

    public static Game getGame() {
        Room room1 = new Room("R1");

        Item pieza1 = new Item("1");
        Item pieza2 = new Item("2");
        Item pieza3 = new Item("3");

        //Creo 3 stacks
        StackContainerComponent stack1 = new StackContainerComponent("stack1",3);
        StackContainerComponent stack2 = new StackContainerComponent("stack2",3);
        StackContainerComponent stack3 = new StackContainerComponent("stack3",3);

        //agrego piezas a un stack
        stack1.addComponent(pieza1);
        stack1.addComponent(pieza2);
        stack1.addComponent(pieza3);

        stack1.addAction(new MoveAction());
        stack1.addAction(new CheckAction());
        stack2.addAction(new MoveAction());
        stack2.addAction(new CheckAction());
        stack3.addAction(new MoveAction());
        stack3.addAction(new CheckAction());
        
        //agrego las 3 pilas al room
        room1.addContainerComponent(stack1);
        room1.addContainerComponent(stack2);
        room1.addContainerComponent(stack3);

        Player player = new Player(room1);
        player.setMaxInventory(1);
        Game game = new Game(player);

        return game;


    }

}