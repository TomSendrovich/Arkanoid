package animation;

import biuoop.DrawSurface;
import removal.Counter;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class LoseAnimation implements Animation {
    private Counter score;

    /**
     * Constructor.
     * @param score - a Counter object.
     */
    public LoseAnimation(Counter score) {
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(55, d.getHeight() / 2, "Game Over! Your score is: " + score.getValue(), 50);


    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
