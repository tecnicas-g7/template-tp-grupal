package ar.fiuba.tdd.tp.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fran on 24/04/16.
 */
public class Game {

    private List<Room> rooms;
    private Character character;
    private Room activeRoom;

    public Game(Character character) {
        this.rooms = new ArrayList<Room>();
        this.character = character;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void setActiveRoom(int roomIndex) {
        Room room = this.rooms.get(roomIndex);
        if (room != null) {
            this.activeRoom = room;
        }
    }

    //Metodos creados para q findbugs no joda
    public Room getActiveRoom() {
        return this.activeRoom;
    }

    public Character getCharacter() {
        return this.character;
    }

}
