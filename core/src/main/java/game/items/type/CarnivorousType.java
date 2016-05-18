package game.items.type;

/**
 Created by nico on 26/04/16.
 */
public class CarnivorousType extends Type{

    public CarnivorousType() {
        super();
        this.getEnemyTypes().add(HerbivorousType.class);
    }


}
