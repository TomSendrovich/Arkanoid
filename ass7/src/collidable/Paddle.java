package collidable;

import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private int width;
    private int speed;

    /**
     * Constructor.
     *
     * @param keyboard - KeyboardSensor object.
     * @param width    - the paddle's width.
     * @param speed    - the paddle's speed.
     */
    public Paddle(KeyboardSensor keyboard, int width, int speed) {
        this.rectangle = new Rectangle(new Point((800 - width) / 2, 580), width, 20);
        this.keyboard = keyboard;
        this.color = Color.ORANGE;
        this.speed = speed;
        this.width = width;
    }

    /**
     * moveLeft - if the player press the left key, the paddle moves left till the end of the screen.
     */
    public void moveLeft() {
        if (this.getCollisionRectangle().getUpperLeft().getX() - speed >= 25) {
            this.rectangle.setX(this.getCollisionRectangle().getUpperLeft().getX() - speed);
            this.rectangle = new Rectangle(this.rectangle.getUpperLeft(),
                    this.rectangle.getWidth(), this.rectangle.getHeight());

        } else if (this.getCollisionRectangle().getUpperLeft().getX() >= 25) {
            this.rectangle.setX(25 + 2);
            this.rectangle = new Rectangle(this.rectangle.getUpperLeft(),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }

    }

    /**
     * moveRight - if the player press the right key, the paddle moves right till the end of the screen.
     */
    public void moveRight() {
        if (this.getCollisionRectangle().getUpperLeft().getX()
                + speed <= 775 - this.getCollisionRectangle().getWidth()) {
            this.getCollisionRectangle().setX(this.getCollisionRectangle().getUpperLeft().getX() + speed);
            this.rectangle = new Rectangle(this.rectangle.getUpperLeft(),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        } else if (this.getCollisionRectangle().getUpperLeft().getX() <= 775
                - this.getCollisionRectangle().getWidth()) {
            this.getCollisionRectangle().setX(775 - this.getCollisionRectangle().getWidth() - 2);
            this.rectangle = new Rectangle(this.rectangle.getUpperLeft(),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }

    }

    // Sprite

    /**
     * timePassed - if the player press the left arrow key, moveLeft method activates.
     * if the player press the right arrow key, moveRight method activates.
     */
    public void timePassed() {

        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawOn - draw the paddle to the screen.
     *
     * @param d - a DrawSurfaec object.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
    }

    /**
     * @return the rectangle object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit - Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  - the point of collision of the ball with the block.
     * @param currentVelocity - the current Volocity of the ball.
     * @param hitter          - the ball that hit the Block.
     * @return The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.getCollisionRectangle().getTopBorder().onLine(this.getCollisionRectangle().getTopBorder(),
                collisionPoint)
                || this.getCollisionRectangle().getLeftBorder().onLine(this.getCollisionRectangle().getTopBorder(),
                collisionPoint)
                || this.getCollisionRectangle().getRightBorder().onLine(this.getCollisionRectangle().getTopBorder(),
                collisionPoint)) {
            if (Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) <= width * (0.2)) {
                return Velocity.fromAngleAndSpeed(330,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                                + Math.pow(currentVelocity.getDy(), 2)));
            }
            if (Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) > width * (0.2)
                    && Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) <= width * (0.4)) {
                return Velocity.fromAngleAndSpeed(300,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                                + Math.pow(currentVelocity.getDy(), 2)));
            }
            if (Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) > width * (0.4)
                    && Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) <= width * (0.6)) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);

            }
            if (Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) > width * (0.6)
                    && Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) <= width * (0.8)) {
                return Velocity.fromAngleAndSpeed(30,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                                + Math.pow(currentVelocity.getDy(), 2)));
            }
            if (Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) > width * (0.8)
                    && Math.abs(this.rectangle.getUpperLeft().getX() - collisionPoint.getX()) <= width) {
                return Velocity.fromAngleAndSpeed(60,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                                + Math.pow(currentVelocity.getDy(), 2)));
            }

            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }
        if (this.getCollisionRectangle().getLeftBorder().onLine(this.getCollisionRectangle().getLeftBorder(),
                collisionPoint)) {
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }

        if (this.getCollisionRectangle().getRightBorder().onLine(this.getCollisionRectangle().getRightBorder(),
                collisionPoint)) {
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * addToGame - Add this paddle to the game.
     *
     * @param g - a game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}