package collidable;

import geometry.Point;

/**
 * @author - Tom Sendrovich.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx = 0;
    private double dy = 0;

    /**
     * constructor.
     *
     * @param dx the direction of the ball.
     * @param dy the speed of the ball.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * fromAngleAndSpeed - turns dx, dy to angle and speed and return a new Velocity variable.
     *
     * @param angle - double variable.
     * @param speed - double variable.
     * @return Velocity variable.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = (Math.sin(Math.toRadians(angle)) * speed);
        double dy = -1 * (Math.cos(Math.toRadians(angle)) * speed);
        return new Velocity(dx, dy);
    }

    /**
     * getDx - return the direction of the ball.
     *
     * @return double variable.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy - return the speed of the ball.
     *
     * @return double variable.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * applyToPoint - Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy).
     *
     * @param p - Point variable.
     * @return - a new Point variable.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}