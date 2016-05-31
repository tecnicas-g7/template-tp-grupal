package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.game.Controller;
import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.types.RiverCrossing;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RiverCrossingTests {


    private void simpleCross(Controller controller, String animal, String moveTo) {
        controller.interpretCommand("take " + animal);
        controller.interpretCommand("cross " + moveTo);
        controller.interpretCommand("leave " + animal);
    }

    @Test
    public void riverCrossingVictory() {

        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);

        simpleCross(controller,"sheep", "north-shore");

        controller.interpretCommand("cross south-shore");

        simpleCross(controller, "wolf", "north-shore");

        controller.interpretCommand("take sheep");
        controller.interpretCommand("cross south-shore");
        controller.interpretCommand("leave sheep");


        simpleCross(controller,"cabbage", "north-shore");

        controller.interpretCommand("cross south-shore");

        simpleCross(controller, "sheep", "north-shore");


        assertTrue(riverCrossing.verifyVictory());

    }

    @Test
    public void riverCrossingFail() {

        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);

        String takeSheep = "take sheep";
        String leaveSheep = "leave sheep";

        String takeWolf = "take wolf";
        String leaveWolf = "leave wolf";

        String northShore = "cross north-shore";
        String crossSouth = "cross south-shore";

        controller.interpretCommand(takeSheep);
        controller.interpretCommand(northShore);
        controller.interpretCommand(leaveSheep);

        controller.interpretCommand(crossSouth);

        controller.interpretCommand(takeWolf);
        controller.interpretCommand(northShore);
        controller.interpretCommand(leaveWolf);


        assertTrue(!riverCrossing.verifyVictory());
    }

    @Test
    public void moreItemsThaAllowed() {
        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);

        String takeSheep = "take sheep";
        String takeWolf = "take wolf";
        controller.interpretCommand(takeSheep);
        controller.interpretCommand(takeWolf);
        Assert.assertTrue(riverCrossing.getPlayer().getInventory().size() == 1);
    }

    @Test
    public void getWrongItem() {
        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);
        String takeWrongItem = "take wrongItem";
        controller.interpretCommand(takeWrongItem);
        Assert.assertTrue(riverCrossing.getPlayer().getInventory().isEmpty());
    }

    @Test
    public void forbiddenMove() {

        Game riverCrossing = (new RiverCrossing()).getGame();
        Controller controller = new Controller(riverCrossing);

        String takeCabbage = "take cabbage";
        String northShore = "cross north-shore";


        controller.interpretCommand(takeCabbage);
        controller.interpretCommand(northShore);
        //No se pudo mover.
        Assert.assertTrue(riverCrossing.getPlayer().getRoom().getName().equals("south-shore"));
    }
}
