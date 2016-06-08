import game.Location;
import game.Player;
import game.actions.Action;
import game.actions.MoveItemAction;
import game.conditions.ContainerCondition;
import game.conditions.PlayerStateCondition;
import game.items.Actionable;
import game.items.StackContainerComponent;
import game.states.StatePlayer;
import game.tasks.DeadLine;
import model.Game;
import model.GameBuilder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by nicol on 18/5/2016.
 */
public class HanoiTower implements GameBuilder {

    public Game build() {

        //Creo 3 stacks
        StackContainerComponent stack1 = new StackContainerComponent("stack1",3);
        StackContainerComponent stack2 = new StackContainerComponent("stack2",3);
        StackContainerComponent stack3 = new StackContainerComponent("stack3",3);
        addActionsToStacks(stack1,stack2,stack3);

        //Creo piezas
        Actionable pieza1 = new Actionable("1");
        Actionable pieza2 = new Actionable("2");
        Actionable pieza3 = new Actionable("3");

        //agrego piezas a un stack (de mayor a menor)
        stack1.addComponent(pieza3);
        stack1.addComponent(pieza2);
        stack1.addComponent(pieza1);

        //agrego las 3 pilas al room
        Location room1 = new Location("R1");
        room1.addItem(stack1);
        room1.addItem(stack2);
        room1.addItem(stack3);

        Player player = createPlayer(room1);
        player.setMaxInventory(1);
        Game game = new Game(player);

        addConditions(game, stack2, stack3);
        game.addLoseCondition(new PlayerStateCondition(new StatePlayer("dead")));
        game.addTask(new DeadLine(game),120000,150000);


        return game;
    }

    @Override
    public String getName() {
        return "hanoiTower";
    }

    private static void addConditions(Game game, StackContainerComponent stack2, StackContainerComponent stack3) {

        List<StackContainerComponent> containers = new ArrayList<>();
        containers.add(stack2);
        containers.add(stack3);
        game.addCondition(new ContainerCondition(containers));
    }

    private void addActionsToStacks(StackContainerComponent stack1, StackContainerComponent stack2, StackContainerComponent stack3) {
        stack1.addAction(new MoveItemAction(null, null, "move"));
        stack1.addAction(createCheckAction());
        stack2.addAction(new MoveItemAction(null, null, "move"));
        stack2.addAction(createCheckAction());
        stack3.addAction(new MoveItemAction(null, null, "move"));
        stack3.addAction(createCheckAction());
    }

    private Action createCheckAction() {
        return new Action("check") {
            @Override
            public String execute(String[] tokens, Player player, Actionable item) {
                try {
                    return "Size of top from " + tokens[1] + " is "
                            + String.valueOf(player.getRoom().getItem(tokens[1]).getLast().getName());
                } catch (Exception e) {
                    return tokens[1] + " is empty.";
                }
            }
        };
    }

    public String getHelp() {
        return "It consists of three rods, and a number of disks of different sizes which can slide onto any rod. ";
    }
}
