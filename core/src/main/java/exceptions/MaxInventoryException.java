package exceptions;

/**
  Created by fran on 24/04/16.
 */
public class MaxInventoryException extends Exception {

    public MaxInventoryException() {
        super("Your inventory is full!");
    }
}
