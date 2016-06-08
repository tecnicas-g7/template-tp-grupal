package game.states;

public interface State {

    String getID();

    String getDescription();

    boolean equals(State state);
}
