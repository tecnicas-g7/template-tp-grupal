package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.exceptions.ItemNotFoundException;
import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.HasItems;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.utils.Messages;

//Created by fran on 17/05/16.

public class MoveItemAction extends Action {

    //private String name;
    private HasItems origin;
    private HasItems destination;

    public MoveItemAction(HasItems origin, HasItems destination) {
        super("move");
        this.origin = origin;
        this.destination = destination;
    }

    public MoveItemAction(HasItems origin, HasItems destination, String name) {
        super(name);
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        if (checkConditions(player)) {
            if (origin == null && destination == null) {
                return moveFromTokenToToken(tokens, player, item);
            }
            return originToDestination(tokens, player, item);
        }
        return "Can't do that";
    }

    private String originToDestination(String[] tokens, Player player, Actionable item) {
        //pick -> origen = room (dinamico...) , destino = player /// drop -> origen = player, destino = room (dinamico...)
        HasItems newOrigin = origin;
        HasItems newDestination = destination;
        if (newOrigin == null) {
            newOrigin = player.getRoom();
        }
        if (tokens.length == 2) {
            return moveDefaultDestination(newOrigin, newDestination, player,item);
        } else {
            return moveSpecifiedDestination(tokens, newOrigin, player, item);
        }

    }

    private String moveDefaultDestination(HasItems newOrigin, HasItems newDestination, Player player, Actionable item) {
        if (newDestination == null) {
            newDestination = player.getRoom();
        }
        return move(player, item, newOrigin, newDestination);
    }

    private String moveSpecifiedDestination(String[] tokens, HasItems newOrigin, Player player, Actionable item) {
        String name = tokens[2];
        Actionable destination;
        try {
            destination = player.getItem(name);
        } catch (ItemNotFoundException e) {
            destination = player.getRoom().getItem(name);
        }
        return move(player,item,newOrigin,destination);
    }

    private String move(Player player, Actionable item, HasItems newOrigin, HasItems newDestination) {
        try {
            newDestination.addItem(item);
            newOrigin.removeItem(item.getName());
            return Messages.getMessage("objectMoved");
        } catch (MaxInventoryException e) {
            return e.getMessage();
        }
    }

    private String move( Player player, Actionable item, HasItems newOrigin, Actionable newDestination) {
        newDestination.addComponent(item);
        newOrigin.removeItem(item.getName());
        return Messages.getMessage("objectMoved");
    }

    private String move(Actionable stackFrom, Actionable stackAfter) throws MaxInventoryException {
        Actionable item = stackFrom.getLast();
        if (stackFrom.getSize() > 0 && stackAfter.isValidMovement(item)) {
            stackFrom.removeComponent(item);
            stackAfter.addComponent(item);
            return Messages.getMessage("moved");
        }
        return Messages.getMessage("cantMove");
    }

    //Si ambos null quiere decir que origen y destino especificado en tokens. tokens[1] = origen tokens[2] = destino;
    private String moveFromTokenToToken(String[] tokens, Player player, Actionable item) {
        try {
            Location room = player.getRoom();
            //String result = move(player, room.getItem(tokens[1]), room.getItem(tokens[2]));
            return move(room.getItem(tokens[1]), room.getItem(tokens[2]));
        } catch (MaxInventoryException e) {
            //Do nothing
            return null;
        }
    }
}
