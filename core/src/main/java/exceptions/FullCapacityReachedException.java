package exceptions;

import game.utils.Messages;

/*
Created by fran on 24/04/16.
*/


public class FullCapacityReachedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FullCapacityReachedException() {
        super(Messages.getMessage("youHaveReachedFullCapacity"));
    }

}