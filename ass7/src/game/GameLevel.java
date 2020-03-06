package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.KeyboardSensor;
import collidable.Block;
import collidable.Collidable;
import collidable.Paddle;
import collidable.Velocity;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import listener.ScoreTrackingListener;
import removal.BallRemover;
import removal.BlockRemover;
import removal.Counter;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import sprites.LivesIndicator;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.LevelIndicator;
import sprites.Ball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Tom Sendrovich.
 */
public class GameLevel implements Animation {
    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle = null;
    private Counter countBlocks = new Counter(0);
    private Counter countBalls = new Counter(0);
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation info;


    /**
     * Constructor.
     *
     * @param info  - a Level Information object.
     * @param gui   - a GUI object.
     * @param score - a Counter object.
     * @param lives - a Counter object.
     */
    public GameLevel(LevelInformation info, GUI gui, Counter score, Counter lives) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.info = info;
        this.score = score;
        this.lives = lives;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * addCollidable - adds a collidable object to the gameEnvironment object.
     *
     * @param c - a collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite - adds a Sprite object to the gameEnvironment object.
     *
     * @param s - a Sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * initialize - Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        Sprite s = info.getBackground();
        s.addToGame(this);
        BlockRemover removeBlock = new BlockRemover(this, countBlocks);
        BallRemover removeBall = new BallRemover(this, countBalls);
        ScoreTrackingListener scoreUpdate = new ScoreTrackingListener(score);
        ScoreIndicator scoreIndicate = new ScoreIndicator(score);
        LivesIndicator livesIndicate = new LivesIndicator(lives);
        LevelIndicator levelIndicator = new LevelIndicator(info.levelName());
        // setting border blocks
        //left border
        Block block1 = new Block(new Rectangle(new Point(0, 40), 25, 760),
                Color.DARK_GRAY, 0);
        block1.addToGame(this);
        //right border
        Block block2 = new Block(new Rectangle(new Point(775, 40), 25, 760),
                Color.DARK_GRAY, 0);
        block2.addToGame(this);
        //top border
        Block block3 = new Block(new Rectangle(new Point(0, 40), 800, 25),
                Color.DARK_GRAY, 0);
        block3.addToGame(this);
        //bottom border
        Block block4 = new Block(new Rectangle(new Point(40, 600), 760, 40),
                Color.DARK_GRAY, 0);
        block4.addToGame(this);
        block4.addHitListener(removeBall);
        List<Block> blocks = info.blocks();
        for (Block b : blocks) {
            b.addToGame(this);
            b.addHitListener(removeBlock);
            b.addHitListener(scoreUpdate);
            countBlocks.increase(1);
        }
        livesIndicate.addToGame(this);
        scoreIndicate.addToGame(this);
        levelIndicator.addToGame(this);


    }

    /**
     * run - Run the game - start the animation loop.
     */
    public void playOneTurn() {

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        if (paddle != null) {
            deletePaddle();
        }
        runner = new AnimationRunner(gui, 60, new Sleeper());
        if (countBlocks.getValue() != 0) {
            createPaddle();
            createBalls(); // or a similar method
            this.runner.run(new CountdownAnimation(2, 3, sprites));
        }
        this.runner.run(this);
        if (countBalls.getValue() == 0) {
            lives.decrease(1);
        }
    }

    /**
     * deletePaddle - deletes the paddle from the sprites and environment collections.
     */
    public void deletePaddle() {
        this.sprites.removeSprite(paddle);
        this.environment.removeCollidable(paddle);
    }

    /**
     * createPaddle - creates the Paddle in the middle of the screen and adds it to the the game.
     */
    public void createPaddle() {
        paddle = new Paddle(gui.getKeyboardSensor(), info.paddleWidth(), info.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * createBalls - create tha balls in the game.
     */
    public void createBalls() {
        List<Velocity> velocities = info.initialBallVelocities();
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < info.numberOfBalls(); i++) {
            balls.add(new Ball(400, 495, 7, Color.GREEN, velocities.get(i), this.environment));
            balls.get(i).setBorder(0, 1500);
            balls.get(i).addToGame(this);
            countBalls.increase(1);

        }
    }

    /**
     * run - run a game with 4 lives.
     */
    public void run() {
        while (lives.getValue() != 0) {
            playOneTurn();
            lives.decrease(1);
        }
        if (countBlocks.getValue() == 0) {
            score.increase(100);
        }
        System.out.println("your score is: " + score.getValue());
        gui.close();
        return;
    }

    /**
     * removeCollidable - removes a collidable object from the gameEnvironment object.
     *
     * @param c - a collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removeSprite - removes a Sprite object from the gameEnvironment object.
     *
     * @param s - a Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        if (countBlocks.getValue() == 0) {
            this.running = false;
        }
        if (countBalls.getValue() == 0) {
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return the number of lives.
     */
    public Counter numLives() {
        return lives;
    }

    /**
     * @return the number of blocks remain.
     */
    public Counter numBlocks() {
        return countBlocks;
    }

    /**
     * @return the current score.
     */
    public Counter numScore() {
        return score;
    }
}
