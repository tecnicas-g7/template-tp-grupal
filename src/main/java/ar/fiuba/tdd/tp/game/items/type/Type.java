package ar.fiuba.tdd.tp.game.items.type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nico on 26/04/16.
 */
public class Type {

    private String name;
    private List<Class<? extends Type>> enemyTypes;


    public Type() {
        enemyTypes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Class<? extends Type>> getEnemyTypes() {
        return enemyTypes;
    }

    public void setEnemyTypes(List<Class<? extends Type>> enemyTypes) {
        this.enemyTypes = enemyTypes;
    }

    public Boolean isEnemy(Type type) {
        return enemyTypes.stream().anyMatch(t -> t == type.getClass());
    }

}
