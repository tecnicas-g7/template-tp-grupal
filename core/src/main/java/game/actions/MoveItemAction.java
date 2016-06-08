package game.actions;

import exceptions.ItemNotFoundException;
import exceptions.MaxInventoryException;
import game.HasItems;
import game.Location;
import game.Player;
import game.items.Actionable;
import game.utils.Messages;


//Created by fran on 17/05/16.

public class MoveItemAction extends Action {

    private boolean originPlayer;
    private boolean destinationPlayer;

    public MoveItemAction(boolean originPlayer, boolean destinationPlayer) {
        super("move");
        this.originPlayer = originPlayer;
        this.destinationPlayer = destinationPlayer;
    }

    public MoveItemAction(boolean originPlayer, boolean destinationPlayer, String name) {
        super(name);
        this.originPlayer = originPlayer;
        this.destinationPlayer = destinationPlayer;
    }

    @Override
    public String execute(String[] tokens, Player player, Actionable item) {
        if (checkConditions(player)) {
            if (!originPlayer && !destinationPlayer) {
                return moveFromTokenToToken(tokens, player, item);
            }
            return originToDestination(tokens, player, item);
        }
        return "Can't do that";
    }

    private String originToDestination(String[] tokens, Player player, Actionable item) {
        //pick -> origen = room (dinamico...) , destino = player /// drop -> origen = player, destino = room (dinamico...)
        HasItems newOrigin;
        if (!originPlayer) {
            newOrigin = player.getRoom();
        } else {
            newOrigin = player;
        }
        if (tokens.length == 2) {
            return moveDefaultDestination(newOrigin, player,item);
        } else {
            return moveSpecifiedDestination(tokens, newOrigin, player, item);
        }

    }

    private String moveDefaultDestination(HasItems newOrigin, Player player, Actionable item) {
        HasItems newDestination;
        if (!destinationPlayer) {
            newDestination = player.getRoom();
        } else {
            newDestination = player;
        }
        return move(item, newOrigin, newDestination);
    }

    private String moveSpecifiedDestination(String[] tokens, HasItems newOrigin, Player player, Actionable item) {
        String name = tokens[2];
        Actionable destination;
        try {
            destination = player.getItem(name);
        } catch (ItemNotFoundException e) {
            destination = player.getRoom().getItem(name);
        }
        return move(item,newOrigin,destination);
    }

    private String move(Actionable item, HasItems newOrigin, HasItems newDestination) {
        try {
            newDestination.addItem(item);
            newOrigin.removeItem(item.getName());
            return Messages.getMessage("objectMoved");
        } catch (MaxInventoryException e) {
            return e.getMessage();
        }
    }

    private String move(Actionable item, HasItems newOrigin, Actionable newDestination) {
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
