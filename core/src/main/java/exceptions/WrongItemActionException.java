package exceptions;

import game.utils.Messages;

/**
  Created by fran on 25/04/16.
 */
public class WrongItemActionException  extends Exception {

    public WrongItemActionException() {
        super(Messages.getMessage("actionCantbeAppliedToItem"));
    }
}