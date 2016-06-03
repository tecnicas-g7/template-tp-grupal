package exceptions;


import game.utils.Messages;

/*
  Created by fran on 24/04/16.
 */
public class ContainerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ContainerNotFoundException() {
        super(Messages.getMessage("containerNotFound"));
    }

}