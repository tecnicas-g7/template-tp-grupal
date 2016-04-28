package ar.fiuba.tdd.tp.exceptions;

/*
  Created by fran on 24/04/16.
 */
public class ItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ItemNotFoundException() {
        super("Item not found!");
    }

}