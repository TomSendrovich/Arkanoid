import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.GameFlow;
import levels.DirectHit;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;
import levels.FinalFour;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Sendrovich.
 */
public class Ass6Game {
    /**
     * main - if there are arguments in args array, creates the game according to that, else creates 4 levels in order.
     *
     * @param args - doesnt get any parameter from the user.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Game", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 60, new Sleeper());
        KeyboardSensor ks = ar.getGui().getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(ar, ks);
        List<LevelInformation> levels = new ArrayList<>();

        if (args.length != 0) {
            for (String s : args) {
                switch (s) {
                    case "1":
                        levels.add(new DirectHit());
                        break;
                    case "2":
                        levels.add(new WideEasy());
                        break;
                    case "3":
                        levels.add(new Green3());
                        break;
                    case "4":
                        levels.add(new FinalFour());
                        break;
                    default:
                        continue;
                }
            }
        }
        if (levels.size() == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
       // gameFlow.runGame(levels);
      //  gameFlow.runLevels(levels);
    }
}
