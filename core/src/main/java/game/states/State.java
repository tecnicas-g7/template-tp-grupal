package game.states;

public interface State {

    String getID();

    String getDescription();

    boolean equals(State state);

    void modifyStatus(String newStatus);

    boolean equalState(String open);
}
