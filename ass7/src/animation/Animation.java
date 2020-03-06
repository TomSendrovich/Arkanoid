package animation;

import biuoop.DrawSurface;

/**
 * @author Tom Sendrovich.
 */
public interface Animation {
    /**
     * doOneFrame - what to do at each frame.
     *
     * @param d - a DrawSurface object.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if we need to stop, false otherwise.
     */
    boolean shouldStop();
}