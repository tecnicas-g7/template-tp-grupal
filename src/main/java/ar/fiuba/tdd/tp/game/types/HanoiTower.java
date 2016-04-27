package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.game.Door;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Room;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Item;

/**
 * Created by ltessore on 25/04/16.
 */
public class HanoiTower {

    public Game getGame() {
        Room room1 = new Room("R1");
        Room room2 = new Room("R2");
        Room room3 = new Room("R3");

        Item item12 = new Item("12");
        Item item13 = new Item("13");
        Item item21 = new Item("21");
        Item item23 = new Item("23");
        Item item31 = new Item("31");
        Item item32 = new Item("32");

        //Create doors
        room1.addDoor(room2,item12);
        room1.addDoor(room3,item13);

        room2.addDoor(room3,item23);
        room2.addDoor(room1,item21);

        room3.addDoor(room2,item32);
        room3.addDoor(room1,item31);

        //Agrego fichas a la torre 1
        room1.addItem(new Item("5"));
        room1.addItem(new Item("4"));
        room1.addItem(new Item("3"));
        room1.addItem(new Item("2"));
        room1.addItem(new Item("1"));

        Player player = new Player(room1);
        Game game = new Game(player);
        game.addRoom(room1);
        game.addRoom(room2);
        game.addRoom(room3);

        return game;
    }


}
