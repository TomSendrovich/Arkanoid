package removal;

import collidable.Block;
import game.GameLevel;
import listener.HitListener;
import sprites.Ball;

/**
 * @author Tom Sendrovich.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param gameLevel         - a GameLevel object.
     * @param removedBalls - a Counter object.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * Balls that hit the "death Region" a being removed.
     *
     * @param beingHit - The Block that is hit right now.
     * @param hitter   - the ball that hit the beingHit Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);

    }

}
