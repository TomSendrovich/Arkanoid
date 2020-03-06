package collidable;

import geometry.Point;

/**
 * @author Tom Sendrovich.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     * @param collisionPoint -  the point of collision of the ball with the block.
     * @param collisionObject - the specific object the we are going to collide with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
