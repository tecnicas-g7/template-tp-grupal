package exceptions;

/**
  Created by fran on 25/04/16.
 */
public class WrongItemActionException  extends Exception {

    public WrongItemActionException() {
        super("Action can't be applied to item!");
    }
}