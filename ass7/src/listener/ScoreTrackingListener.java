package listener;

import collidable.Block;
import removal.Counter;
import sprites.Ball;

/**
 * @author Tom Sendrovich.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter - a Counter object.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumHits() == 0) {
            currentScore.increase(10);
        } else {
            currentScore.increase(5);
        }
    }
}