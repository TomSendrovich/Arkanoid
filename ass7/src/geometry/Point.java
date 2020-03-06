package geometry;

/**
 * @author Tom Sendrovich.
 */
public class Point {
    private double x;
    private double y;

    /**
     * @param x a coordinate.
     * @param y a coordinate.
     */
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other a Point.
     * @return the distance between two points.
     */

    public double distance(Point other) {
        double distance = Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
        return distance;
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other a Point.
     * @return true if the the coordinates are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * getX - return the x coordinate.
     *
     * @return the x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * geYX - return the Y coordinate.
     *
     * @return the y coordinate.
     */
    public double getY() {
        return this.y;
    }
}
