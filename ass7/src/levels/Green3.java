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
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity v = Velocity.fromAngleAndSpeed(60, 8);
        velocities.add(v);
        Velocity v1 = Velocity.fromAngleAndSpeed(300, 8);
        velocities.add(v1);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Sprite s = new Green3Back();
        return s;
    }

    @Override
    public List<Block> blocks() {
        List blocks = new ArrayList();
        for (int i = 1; i <= 7; i++) {
            if (i == 1) {
                Block block = new Block(new Rectangle(new Point(270, 200), 70, 35),
                        Color.BLUE, 2);
                blocks.add(block);

            } else {
                Block block = new Block(new Rectangle(new Point(270 + 70 * (i - 1), 200), 70, 35),
                        Color.BLUE, 2);
                blocks.add(block);

            }
        }
        for (int i = 1; i <= 6; i++) {
            if (i == 1) {
                Block block = new Block(new Rectangle(new Point(340, 235), 70, 35),
                        Color.RED, 1);
                blocks.add(block);


            } else {
                Block block = new Block(new Rectangle(new Point(340 + 70 * (i - 1), 235), 70, 35),
                        Color.RED, 1);
                blocks.add(block);

            }
        }
        for (int i = 1; i <= 5; i++) {
            if (i == 1) {
                Block block = new Block(new Rectangle(new Point(410, 270), 70, 35),
                        Color.YELLOW, 1);
                blocks.add(block);

            } else {
                Block block = new Block(new Rectangle(new Point(410 + 70 * (i - 1), 270), 70, 35),
                        Color.YELLOW, 1);
                blocks.add(block);

            }
        }
        for (int i = 1; i <= 4; i++) {
            if (i == 1) {
                Block block = new Block(new Rectangle(new Point(480, 305), 70, 35),
                        Color.GREEN, 1);
                blocks.add(block);

            } else {
                Block block = new Block(new Rectangle(new Point(480 + 70 * (i - 1), 305), 70, 35),
                        Color.GREEN, 1);
                blocks.add(block);

            }
        }
        for (int i = 1; i <= 3; i++) {
            if (i == 1) {
                Block block = new Block(new Rectangle(new Point(550, 340), 70, 35),
                        Color.PINK, 1);
                blocks.add(block);

            } else {
                Block block = new Block(new Rectangle(new Point(550 + 70 * (i - 1), 340), 70, 35),
                        Color.PINK, 1);
                blocks.add(block);

            }
        }
        for (int i = 1; i <= 2; i++) {
            if (i == 1) {
                Block block = new Block(new Rectangle(new Point(620, 375), 70, 35),
                        Color.ORANGE, 1);
                blocks.add(block);

            } else {
                Block block = new Block(new Rectangle(new Point(620 + 70 * (i - 1), 375), 70, 35),
                        Color.ORANGE, 1);
                blocks.add(block);

            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 27;
    }
}
