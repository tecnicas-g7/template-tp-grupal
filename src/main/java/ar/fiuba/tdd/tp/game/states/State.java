package ar.fiuba.tdd.tp.game.states;

public interface State {

    String getID();

    String getDescription();

    boolean equals(State state);
}
