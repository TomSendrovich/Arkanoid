package listener;

import collidable.Block;
import sprites.Ball;

/**
 * @author Tom Sendrovich.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * hitEvent - Determines what happens when a ball hit a Block.
     *
     * @param beingHit - The Block that is hit right now.
     * @param hitter   - the ball that hit the beingHit Block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}