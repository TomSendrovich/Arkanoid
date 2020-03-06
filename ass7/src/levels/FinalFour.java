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
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(45, 5);
        velocities.add(v1);
        Velocity v2 = Velocity.fromAngleAndSpeed(0, 5);
        velocities.add(v2);
        Velocity v3 = Velocity.fromAngleAndSpeed(315, 5);
        velocities.add(v3);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Sprite s = new FinalFourBack();
        return s;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(new Rectangle(new Point(40 + (i * 60), 150), 60, 25), Color.GRAY, 1));
        }
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(new Rectangle(new Point(40 + (i * 60), 175), 60, 25), Color.RED, 1));
        }
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(new Rectangle(new Point(40 + (i * 60), 200), 60, 25), Color.YELLOW, 1));
        }
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(new Rectangle(new Point(40 + (i * 60), 225), 60, 25), Color.GREEN, 1));
        }
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(new Rectangle(new Point(40 + (i * 60), 250), 60, 25), Color.WHITE, 1));
        }
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(new Rectangle(new Point(40 + (i * 60), 275), 60, 25), Color.PINK, 1));
        }
        for (int i = 0; i < 12; i++) {
            blocks.add(new Block(new Rectangle(new Point(40 + (i * 60), 300), 60, 25), Color.CYAN, 1));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 84;
    }
}
