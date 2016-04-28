package ar.fiuba.tdd.tp.exceptions;

/*
Created by fran on 24/04/16.
*/

public class FullCapacityReachedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FullCapacityReachedException() {
        super("You have reached full capacity!");
    }

}