package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int saveCountFrom;
    private Sleeper sleeper;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param numOfSeconds - a double variable.
     * @param countFrom    - an int variable.
     * @param gameScreen   - a SpriteCollection object.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.saveCountFrom = countFrom;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(50 + d.getHeight() / 2, d.getWidth() / 2, String.valueOf(countFrom), 120);
        if (countFrom != saveCountFrom) {
            this.sleeper.sleepFor((long) ((numOfSeconds / saveCountFrom) * 1000));
        }
        countFrom--;
        if (countFrom == -1) {
            this.stop = true;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
