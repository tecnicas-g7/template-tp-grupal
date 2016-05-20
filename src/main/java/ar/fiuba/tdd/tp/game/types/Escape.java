package ar.fiuba.tdd.tp.game.types;

import ar.fiuba.tdd.tp.exceptions.MaxInventoryException;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.Location;
import ar.fiuba.tdd.tp.game.Player;
import ar.fiuba.tdd.tp.game.actions.EnterAction;
import ar.fiuba.tdd.tp.game.actions.MoveItemAction;
import ar.fiuba.tdd.tp.game.actions.OpenAction;
import ar.fiuba.tdd.tp.game.conditions.InventoryCondition;
import ar.fiuba.tdd.tp.game.items.Actionable;
import ar.fiuba.tdd.tp.game.items.Container;
import ar.fiuba.tdd.tp.game.items.Item;
import ar.fiuba.tdd.tp.game.items.Linker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javier on 5/19/16.
 */
public class Escape implements GameFactory {
    @Override
    public Game getGame() {


        Location pasillo = new Location("pasillo");
        Player player = new Player(pasillo);
        Item lapicera = new Item("lapicera");
        Item foto = new Item("foto");
        player.setMaxInventory(4);
        try {
            player.addItem(foto);
            player.addItem(lapicera);
        } catch (MaxInventoryException e) {
            e.printStackTrace();
        }
        Location acceso = new Location("acceso");
        Location salonTres = new Location("salonTres");
        createSalonUno(player,pasillo,acceso,salonTres);
        createSalonDos(player,pasillo);
        createSalonTres(player,pasillo);
        createAccesoBiblioteca(player,pasillo);










        return null;
    }

    private Location createSalonUno(Player player, Location pasillo, Location acceso, Location salonTres) {
        Location salonUno = new Location("salonUno");
        salonUno.addDoor(pasillo,null,new EnterAction("enter"));
        pasillo.addDoor(salonUno,null,new EnterAction("enter"));
        salonUno.addItem(new Item("mesa"));
        salonUno.addItem(new Item("vaso1"));
        salonUno.addItem(new Item("vaso2"));
        salonUno.addItem(new Item("silla1"));
        salonUno.addItem(new Item("silla2"));
        salonUno.addItem(new Item("cuadroTren"));
        createUsableItems(player,salonUno, acceso, salonTres);


        return salonUno;
    }

    private void createUsableItems(Player player,Location salonUno, Location acceso, Location salonTres) {
        Container cuadroBarco = new Container("cuadroBarco",1);
        cuadroBarco.addAction(new OpenAction("move"));
        Container credencial = new Container("credencial",1);
        Item key = new Item("key");
        addPickDrop(key,player);
        Container cajaFuerte = new Container("cajaFuerte",1);

        try {
            cuadroBarco.addItem(cajaFuerte);
            cajaFuerte.addItem(credencial);
        } catch (MaxInventoryException e) {
            e.printStackTrace();
        }
        OpenAction open = new OpenAction("open");
        List<Actionable> list = new ArrayList<>();
        list.add(key);
        open.addCondition(new InventoryCondition(list,true));
        cajaFuerte.addAction(open);
        addPickDrop(credencial,player);
        Item licor = new Item("licor");
        salonUno.addItem(licor);
        addPickDrop(licor,player);
        createLicorCredentialConditions(credencial,player.getItem("foto"),licor,acceso);
    }

    private void createLicorCredentialConditions(Container credencial, Actionable foto, Item licor, Location acceso) {

    }

    private void addPickDrop(Actionable actionable, Player player) {
        actionable.addAction(new MoveItemAction(null,player,"pick"));
        actionable.addAction(new MoveItemAction(player,null,"drop"));
    }


    @Override
    public String getHelp() {
        return null;
    }
}
