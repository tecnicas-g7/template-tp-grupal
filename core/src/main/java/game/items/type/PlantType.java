package game.items.type;

/**
 Created by nico on 26/04/16.
 */
public class PlantType extends Type{

    public PlantType() {
        super();
        this.setName("Plants");
        this.getEnemyTypes().add(HerbivorousType.class);
    }


}
