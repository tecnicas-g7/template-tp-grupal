package ar.fiuba.tdd.tp.game;

import ar.fiuba.tdd.tp.exceptions.GameNotFoundExcpetion;
import ar.fiuba.tdd.tp.game.types.BoxGame;
import ar.fiuba.tdd.tp.game.types.EnterRoom;
import ar.fiuba.tdd.tp.game.types.StickGame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by fran on 25/04/16.
 */
public class MainTest {

    public static void main(String[] argv) throws Exception {

        System.out.println("Welcome to game!");
        Controller controller = new Controller(getGame("boxGame"));

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String input = inFromUser.readLine();

        boolean finish = false;
        while (!finish) {
            System.out.println(controller.interptetCommand(input));
            if (!controller.verify()) {
                input = inFromUser.readLine();
            } else {
                finish = true;
            }
        }
    }


    public static Game getGame(String gameName) {
        switch (gameName) {
            case "stickGame":
                return StickGame.getGame();
            case "enterRoom":
                return EnterRoom.getGame();
            case "boxGame":
                return BoxGame.getGame();
            default:
                throw new GameNotFoundExcpetion();
        }

    }


}
