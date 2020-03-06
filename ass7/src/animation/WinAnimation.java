package animation;

import biuoop.DrawSurface;
import removal.Counter;

/**
 * @author Tom Sendrovich.
 */
public class WinAnimation implements Animation {
    private Counter score;

    /**
     * Constructor.
     * @param score - a Counter object.
     */
    public WinAnimation(Counter score) {
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(55, d.getHeight() / 2, "You Win! Your score is: " + score.getValue(), 50);

    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}

