package game;

import collidable.Collidable;
import geometry.Line;
import geometry.Point;
import collidable.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Sendrovich.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * addCollidable - add the given collidable to the environment.
     *
     * @param c - a collidable object.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * removeCollidable - removes a collidable object from the gameEnvironment object.
     *
     * @param c - a collidable object.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
    /**
     * removeAllCollidable - removes all collidables objects from the gameEnvironment object.
     *
     */
    public void removeAllCollidable() {
        int size = collidables.size();
        for (int i = 0; i < size; i++) {
            collidables.remove(0);
        }
    }

    /**
     * @return the list of collidables.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * getClosestCollision -  Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection,return
     * null. Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory - a line object.
     * @return - the closest collision point with trajectory line.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minDistance = -1, saveDistance;
        Point p;
        CollisionInfo collisionInfo = new CollisionInfo(null, null);
        if (this.collidables.size() == 0) {
            return null;
        }
        for (Collidable c : collidables) {
            p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p == null) {
                continue;
            }
            saveDistance = trajectory.start().distance(p);
            if (minDistance == -1) {
                minDistance = saveDistance;
                collisionInfo = new CollisionInfo(p, c);
            }
            if (minDistance > saveDistance) {
                minDistance = saveDistance;
                collisionInfo = new CollisionInfo(p, c);
            }
        }
        return collisionInfo;
    }


}
