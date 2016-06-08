package items;

import game.items.Actionable;
import items.type.Type;

/**
 * Created by nicol on 8/6/2016.
 */
public class ActionableType extends Actionable{


    public ActionableType(String name) {
        super(name);
    }

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
