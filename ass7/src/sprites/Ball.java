package sprites;

import collidable.CollisionInfo;
import collidable.Velocity;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;
import listener.HitListener;

import java.util.List;

/**
 * @author Tom Sendrovich.
 */
public class Ball implements Sprite {
    private GameEnvironment environment;
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity v;
    private Point borders;
    private List<HitListener> hitListeners;


    /**
     * constructor.
     *
     * @param center point variable, the location of the ball.
     * @param r      int varivle, the radius.
     * @param color  hava.awt variable, the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point((int) Math.round(center.getX()), (int) Math.round(center.getY()));
        this.radius = r;
        this.color = color;
    }

    /**
     * constructor.
     *
     * @param x     the x coordinate.
     * @param y     the y coordinate.
     * @param r     the radius.
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        Point p = new Point(x, y);
        this.center = p;
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * constructor.
     *
     * @param x     the x coordinate.
     * @param y     the y coordinate.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     * @param v     Velocity variable, speed and directions of the ball.
     * @param game holds a list of collidables, and a list of sprites.
     */
    public Ball(int x, int y, int r, java.awt.Color color, Velocity v, GameEnvironment game) {
        Point p = new Point(x, y);
        this.center = p;
        this.radius = r;
        this.color = color;
        this.v = v;
        this.environment = game;
    }

    /**
     * getX - return the x coordinate of the ball.
     *
     * @return return the x coordinate of the ball.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * getY - return the y coordinate of the ball.
     *
     * @return return the y coordinate of the ball.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * getSize - return the radius of the ball.
     *
     * @return return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * getColor - return the color of the ball.
     *
     * @return return java.awt.Color variable.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn - draw the ball on the given DrawSurface.
     *
     * @param surface DrawSurface variable.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * setVelocity - set the speed and direction of a ball by creatind new Velocity varible.
     *
     * @param dx - direction of the ball.
     * @param dy - speed of the ball.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * getVelocity - return a Velocity variable which indicates the current velocity.
     *
     * @return Velocity variable.
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * setVelocity - set the speed and direction of a ball.
     *
     * @param v1 required velocity for tha ball.
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * setBorder - set the coordinates where the ball can move, by creating Point variable.
     *
     * @param x - x coordinate.
     * @param y - y coordinate.
     */
    public void setBorder(double x, double y) {
        this.borders = new Point(x, y);
    }

    /**
     * moveOneStep - computes the imaginary line that the ball moves to, then calculates the closest
     *              intersection point to that line and changes the velocity accordingly.
     */
    public void moveOneStep() {
        Point nextPosition = this.getVelocity().applyToPoint(this.center);
        Line trajectory = computeTrajectory();
        CollisionInfo collisionInfo = environment.getClosestCollision(trajectory);

        if (collisionInfo.collisionPoint() != null) {
            Point collisionPoint = collisionInfo.collisionPoint();
            if (nextPosition.getX() + this.getVelocity().getDx() >= collisionPoint.getX()
                    && nextPosition.getY() + this.getVelocity().getDy() >= collisionPoint.getY()
                    && this.getVelocity().getDx() >= 0 && this.getVelocity().getDy() >= 0) {
                Velocity v1 = collisionInfo.collisionObject().hit(this, collisionPoint, this.getVelocity());
                setVelocity(v1);
            } else if (nextPosition.getX() + this.getVelocity().getDx() <= collisionPoint.getX()
                    && nextPosition.getY() + this.getVelocity().getDy() >= collisionPoint.getY()
                    && this.getVelocity().getDx() <= 0 && this.getVelocity().getDy() >= 0) {
                Velocity v1 = collisionInfo.collisionObject().hit(this, collisionPoint, this.getVelocity());
                setVelocity(v1);
            } else if (nextPosition.getX() + this.getVelocity().getDx() >= collisionPoint.getX()
                    && nextPosition.getY() + this.getVelocity().getDy() <= collisionPoint.getY()
                    && this.getVelocity().getDx() >= 0 && this.getVelocity().getDy() <= 0) {
                Velocity v1 = collisionInfo.collisionObject().hit(this, collisionPoint, this.getVelocity());
                setVelocity(v1);
            } else if (nextPosition.getX() + this.getVelocity().getDx() <= collisionPoint.getX()
                    && nextPosition.getY() + this.getVelocity().getDy() <= collisionPoint.getY()
                    && this.getVelocity().getDx() <= 0 && this.getVelocity().getDy() <= 0) {
                Velocity v1 = collisionInfo.collisionObject().hit(this, collisionPoint, this.getVelocity());
                setVelocity(v1);
            }
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            if (this.center.getX() + v.getDx() + this.radius > this.borders.getY() && v.getDx() > 0) {
                setVelocity(v.getDx() * -1, v.getDy());
            }
            if (this.center.getY() + v.getDy() + this.radius > this.borders.getY() && v.getDy() > 0) {
                setVelocity(v.getDx(), v.getDy() * -1);
            }
            if (this.center.getX() - v.getDx() - this.radius < this.borders.getX() && v.getDx() < 0) {
                setVelocity(v.getDx() * -1, v.getDy());
            }
            if (this.center.getY() - v.getDy() - this.radius < this.borders.getX() && v.getDy() < 0) {
                setVelocity(v.getDx(), v.getDy() * -1);
            }
            this.center = this.getVelocity().applyToPoint(this.center);
        }

    }

    /**
     *
     * @return compute the "imaginary" line that the ball moves to before it hits the borders.
     */
    public Line computeTrajectory() {
        double x = this.center.getX(), y = this.center.getY();
        boolean t = true;
        while (t) {
            if ((y + this.getVelocity().getDy() < this.borders.getY() && y + this.getVelocity().getDy()
                    > this.borders.getX()) && (x + this.getVelocity().getDx() > this.borders.getX()
                    || x + this.getVelocity().getDx() < this.borders.getY())) {
                x += this.getVelocity().getDx();
                y += this.getVelocity().getDy();
            } else {
                x += this.getVelocity().getDx();
                y += this.getVelocity().getDy();
                t = false;
            }
        }
        return new Line(this.center, new Point(x, y));
    }

    /**
     * remove the object from the sprites list.
     * @param gameLevel - a GameLevel object.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

}
