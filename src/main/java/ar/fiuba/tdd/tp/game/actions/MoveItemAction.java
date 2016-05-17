package ar.fiuba.tdd.tp.game.actions;

import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.HasItems;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.items.Describable;

/**
 * Created by fran on 17/05/16.
 */
public class MoveItemAction implements Action {

    private String name;
    private HasItems origin;
    private HasItems destination;

    public MoveItemAction(HasItems origin, HasItems destination) {
        this.origin = origin;
        this.destination = destination;
        this.name = "move";
    }

    public MoveItemAction(HasItems origin, HasItems destination, String name) {
        this.origin = origin;
        this.destination = destination;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String execute(String[] tokens, Player player, Describable item) {
        if (origin == null && destination == null) {
            return moveFromTokenToToken(tokens,player,item);
        }
        return originToDestination(tokens, player, item);
    }

    private String originToDestination(String[] tokens, Player player, Describable item) {
        //pick -> origen = room (dinamico...) , destino = player /// drop -> origen = player, destino = room (dinamico...)
        HasItems newOrigin = origin;
        HasItems newDestination = destination;
        if (newOrigin == null) {
            newOrigin = player.getRoom();
        }
        if (newDestination == null) {
            newDestination = player.getRoom();
        }
        return move(tokens,player,item,newOrigin,newDestination);
    }

    private String move(String[] tokens, Player player, Describable item, HasItems newOrigin, HasItems newDestination) {
        try {
            newDestination.addItem(item);
            newOrigin.removeItem(item.getName());
            return "Object moved";
        } catch (MaxInventoryException e) {
            return e.getMessage();
        }
    }

    //Si ambos null quiere decir que origen y destino especificado en tokens. tokens[1] = origen tokens[2] = destino;
    private String moveFromTokenToToken(String[] tokens, Player player, Describable item) {
        return null;
    }


}
