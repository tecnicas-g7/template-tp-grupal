package ar.fiuba.tdd.tp.game.items.type;

/**
 * Created by nico on 26/04/16.
 */
public class HerbivorousType extends Type{

    public HerbivorousType() {
        super();
        this.getEnemyTypes().add(CarnivorousType.class);
    }



}
