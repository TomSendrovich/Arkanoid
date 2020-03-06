package levels;

import collidable.Block;
import collidable.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Sendrovich.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity v = Velocity.fromAngleAndSpeed(0, 8);
        velocities.add(v);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 50;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Sprite s = new DirectHitBack();
        return s;
    }

    @Override
    public List<Block> blocks() {
        List blocks = new ArrayList();
        blocks.add(new Block(new Rectangle(new Point(385, 170), 28, 28), Color.RED, 1));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
