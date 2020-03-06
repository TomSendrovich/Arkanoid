package removal;

import collidable.Block;
import game.GameLevel;
import listener.HitListener;
import sprites.Ball;

/**
 * @author Tom Sendrovich.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param gameLevel          - a GameLevel object.
     * @param removedBlocks - a Counter object.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the gameLevel. Remember to remove this listener from the block
     * that is being removed from the gameLevel.
     *
     * @param beingHit - The Block that is hit right now.
     * @param hitter   - the ball that hit the beingHit Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumHits() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(gameLevel);
            remainingBlocks.decrease(1);
        }
    }
}