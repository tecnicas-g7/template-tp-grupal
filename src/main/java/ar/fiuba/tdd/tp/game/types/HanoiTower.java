package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.*;

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
        //Creo 3 pilas
        StackContainerComponent stack1 = new StackContainerComponent("stack1",3);
        StackContainerComponent stack2 = new StackContainerComponent("stack2",3);
        StackContainerComponent stack3 = new StackContainerComponent("stack3",3);
        //agrego piezas a una pila
        stack1.addComponent(pieza1);
        stack1.addComponent(pieza2);
        stack1.addComponent(pieza3);

        //agrego las 3 pilas al room
        room1.addContainerComponent(stack1);
        room1.addContainerComponent(stack2);
        room1.addContainerComponent(stack3);

        Player player = new Player(room1);
        player.setMaxInventory(1);
        Game game = new Game(player);

        return game;


    }

    private void move(Player player, ContainerComponent stackFrom, ContainerComponent stackAfter) throws MaxInventoryException {
        ContainerComponent item = stackAfter.getLast();
        //Player toma pieza
        player.addItem(item);

        //Mover
        if (isMovementValid(player, stackAfter)) {
            System.out.print(" MOVIMIENTO VALIDO \n");
            stackFrom.removeComponent(item);
            stackAfter.addComponent(item);
        } else {
            System.out.print(" MOVIMIENTO INVALIDO \n");
        }
        //Player deja pieza
        player.removeItem(item.getName());

    }

    private Boolean isMovementValid(Player player, ContainerComponent stackAfter) {
        // chequea condiciones de movimiento de la pieza
        Iterator<ContainerComponent> it = player.getInventoryIterator();
        //chequea si el ultimo del stackAfter es mas grande del que tiene el player
        if (Integer.parseInt(stackAfter.getLast().getName()) > Integer.parseInt(it.next().getName())) {
            return true;
        }
        return false;
    }



    public void play(Player player) throws MaxInventoryException{

        move(player,player.getRoom().getItem("stack1"), player.getRoom().getItem("stack2"));

        move(player,player.getRoom().getItem("stack1"), player.getRoom().getItem("stack3"));

        move(player,player.getRoom().getItem("stack2"),player.getRoom().getItem("stack3"));

        move(player,player.getRoom().getItem("stack1"), player.getRoom().getItem("stack2"));

        move(player,player.getRoom().getItem("stack3"),player.getRoom().getItem("stack1"));

        move(player,player.getRoom().getItem("stack3"),player.getRoom().getItem("stack2"));

        move(player,player.getRoom().getItem("stack1"),player.getRoom().getItem("stack2"));
        
        System.out.print("FIN");
    }


}