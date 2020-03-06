package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Tom Sendrovich.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     * @param gui - a GUI object.
     * @param framesPerSecond - an integer.
     * @param sleeper - a Sleeper object.
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * run - the loop of the animation.
     * @param animation - an Animation object.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     *
     * @return a GUI object.
     */
    public GUI getGui() {
        return this.gui;
    }
}