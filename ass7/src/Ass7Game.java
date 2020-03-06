import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.GameFlow;

/**
 * @author Tom Sendrovich.
 */
public class Ass7Game {
    /**
     * main - if there is a given path, start the game with this path. else, starts the game with a default path.
     * @param args - contains the path or nothing.
     */
    public static void main(String[] args) {
        String levelsPath;
        if (args.length == 1) {
            levelsPath = args[0];
        } else {
            levelsPath = null;
        }
        GUI gui = new GUI("Game", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60, new Sleeper());
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(ar, ks);
        gameFlow.runGame(levelsPath);
    }
}
