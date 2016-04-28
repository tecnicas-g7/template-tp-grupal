package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.ContainerComponent;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.conditions.Condition;
import ar.fiuba.tdd.tp.game.items.Item;

import java.util.List;

/**
 * Created by ltessore on 27/04/16.
 */
public class HanoiTower {
    private Room room1;
    private Room room2;
    private Room room3;
    private Player player
    private List<Condition> restrictionList;

    public HanoiTower() {
        this.room1 = new Room("R1");
        this.room2 = new Room("R2");
        this.room3 = new Room("R3");

        Item pieza1 = new Item("1");
        Item pieza2 = new Item("2");
        Item pieza3 = new Item("3");


        //Agrego fichas a la torre 1
        room1.addContainerComponent(pieza3);
        room1.addContainerComponent(pieza2);
        room1.addContainerComponent(pieza1);

        this.player = new Player(room1);

        this.restrictionList = addRestrictions();

    }

    private List<Condition> addRestrictions() {
        List<Condition> conditions = null;
        //TODO Agregar restricciones de movimiento
        return conditions;
    }

    private void move(Item component, Room roomAfter) throws MaxInventoryException {
        //TODO mover la pieza al roomAfter

        //El player toma la pieza
        this.player.addItem(component);

        //Mover
        if (isMovementValid(component,roomAfter)) {
            System.out.print(" MOVIMIENTO VALIDO \n");
            roomAfter.addContainerComponent(component);
        } else {
            System.out.print(" MOVIMIENTO INVALIDO \n");
        }

    }

    private Boolean isMovementValid(ContainerComponent component, Room roomAfter) {
        //TODO chequear condiciones de movimiento de la pieza
        for (Condition r : this.restrictionList) {
            if (!r.isValid(player)) {
                return false;
            }
        }
        return true;
    }


    public void play() {



        /**
         * TODO deberia agregar metodo getLast() en Room y que me devuelva el ultimo
         * Para eso deberia hacer que los items de Room sea una collection para instanciarlo con un stack
         * (COMENTO el move() para que no genere warnings)
        */
        //move(room1.getLast(), room2);


        //move(room1.getLast(), room3);


        this.player.setRoom(room2);
        //move(room2.getLast(), room3);


        this.player.setRoom(room1);
        //move(room1.getLast(), room2);


        this.player.setRoom(room3);
        //move(room3.getLast(), room1);


        this.player.setRoom(room3);
        //move(room3.getLast(), room2);


        this.player.setRoom(room1);
        //move(room1.getLast(), room2);


        System.out.print("FIN");
    }


}