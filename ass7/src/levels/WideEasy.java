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
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int angle = 311;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Velocity v = Velocity.fromAngleAndSpeed(angle, 6);
            velocities.add(v);
            angle += 10;
        }

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Sprite s = new WideEasyBack();
        return s;
    }

    @Override
    public List<Block> blocks() {
        List blocks = new ArrayList();
        blocks.add(new Block(new Rectangle(new Point(40, 270), 60, 30), Color.RED, 1));
        blocks.add(new Block(new Rectangle(new Point(100, 270), 60, 30), Color.RED, 1));
        blocks.add(new Block(new Rectangle(new Point(160, 270), 60, 30), Color.ORANGE, 1));
        blocks.add(new Block(new Rectangle(new Point(220, 270), 60, 30), Color.ORANGE, 1));
        blocks.add(new Block(new Rectangle(new Point(280, 270), 60, 30), Color.YELLOW, 1));
        blocks.add(new Block(new Rectangle(new Point(340, 270), 60, 30), Color.YELLOW, 1));
        blocks.add(new Block(new Rectangle(new Point(400, 270), 60, 30), Color.GREEN, 1));
        blocks.add(new Block(new Rectangle(new Point(460, 270), 60, 30), Color.GREEN, 1));
        blocks.add(new Block(new Rectangle(new Point(520, 270), 60, 30), Color.BLUE, 1));
        blocks.add(new Block(new Rectangle(new Point(580, 270), 60, 30), Color.BLUE, 1));
        blocks.add(new Block(new Rectangle(new Point(640, 270), 60, 30), Color.PINK, 1));
        blocks.add(new Block(new Rectangle(new Point(700, 270), 60, 30), Color.PINK, 1));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 12;
    }
}
