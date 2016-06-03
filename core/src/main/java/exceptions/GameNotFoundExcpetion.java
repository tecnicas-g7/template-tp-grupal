package exceptions;

/*
 Created by fran on 24/04/16.
 */

import game.utils.Messages;

public class GameNotFoundExcpetion extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GameNotFoundExcpetion() {
        super(Messages.getMessage("gameNotFound"));
    }
}
