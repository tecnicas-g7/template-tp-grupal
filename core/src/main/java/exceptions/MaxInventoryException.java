package exceptions;

import game.utils.Messages;

/**
  Created by fran on 24/04/16.
 */
public class MaxInventoryException extends Exception {

    public MaxInventoryException() {
        super(Messages.getMessage("youtInventoryIsFull"));
    }
}
