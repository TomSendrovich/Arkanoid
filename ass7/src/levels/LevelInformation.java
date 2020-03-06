package levels;

import collidable.Block;
import collidable.Velocity;
import sprites.Sprite;

import java.util.List;

/**
 * @author Tom Sendrovich.
 */
public interface LevelInformation {
    /**
     * @return the number of balls in the level.txt.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities - The initial velocity of each ball.
     *
     * @return a List with the velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * @return the level.txt's name.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.txt
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level.txt, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of levels that should be removed before the level.txt is considered to be "cleared".
     */
    int numberOfBlocksToRemove();

}