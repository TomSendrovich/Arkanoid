package animation;

import biuoop.DrawSurface;


/**
 * @author Tom Sendrovich
 */
public class PauseScreen implements Animation {
    /**
     * Constructor.
     */
    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 50);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
