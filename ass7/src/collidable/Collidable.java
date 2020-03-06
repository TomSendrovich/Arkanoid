package collidable;

import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;

/**
 * @author Tom Sendrovich.
 */
public interface Collidable {
    /**
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit - Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint - the point of collision of the ball with the block.
     * @param currentVelocity - the current Volocity of the ball.
     * @param hitter - the ball that hit the Block.
     * @return The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
