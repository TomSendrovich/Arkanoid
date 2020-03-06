package geometry;

import java.util.List;

/**
 * @author Tom Sendrovich.
 * id 205974876.
 */
public class Line {
    private Point start;
    private Point end;
    private double length;

    /**
     * constructor - create new Line object.
     *
     * @param start a Point object.
     * @param end   a Point object.
     */
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor - create new Line object.
     * First creates two points, then set them to start and end.
     *
     * @param x1 x coordinate of point start.
     * @param y1 y coordinate of point start.
     * @param x2 x coordinate of point end.
     * @param y2 y coordinate of point end.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length - return the length of the Line.
     *
     * @return and int, the distance between the two points.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * middle - return a new Point which is the middle coordinates of the line.
     *
     * @return a new Point.
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * start - returns the start point of a line.
     *
     * @return the start point.
     */
    public Point start() {
        return start;
    }

    /**
     * end - return the end point of the line.
     *
     * @return the end point.
     */
    public Point end() {
        return end;
    }

    /**
     * max - return the bigger number.
     *
     * @param num1 double variable.
     * @param num2 double variable.
     * @return return the bigger number.
     */
    public double max(double num1, double num2) {
        if (num1 >= num2) {
            return num1;
        } else {
            return num2;
        }
    }

    /**
     * min - return the smaller number.
     *
     * @param num1 double variable.
     * @param num2 double variable.
     * @return return the smaller number.
     */
    public double min(double num1, double num2) {
        if (num1 <= num2) {
            return num1;
        } else {
            return num2;
        }
    }

    /**
     * onLine - return true if point p is on the line l1, false otherwise.
     *
     * @param l Line object.
     * @param p Point object.
     * @return true if point p is on the line l1, false otherwise.
     */
    public boolean onLine(Line l, Point p) {   //check whether p is on the line or not
        Point p1 = new Point(Math.floor(p.getX()), Math.floor(p.getY()));
        Line l1 = new Line(Math.floor(l.start.getX()), Math.floor(l.start.getY()), Math.floor(l.end.getX()),
                Math.floor(l.end.getY()));
        if (Math.abs(l1.start.distance(p1) + l1.end.distance(p1) - l1.start.distance(l1.end)) <= 1) {
            return true;
        }
        return false;
    }

    /**
     * direction - calculates if two lines are collinear, clockwise direction or anti-clockwise direction.
     *
     * @param a Point object.
     * @param b Point object.
     * @param c Point object.
     * @return 0 - lines are collinear, 1 - lines are clockwise direction, 2 - lines are anti-clockwise direction.
     */
    public int direction(Point a, Point b, Point c) {
        double val = (b.getY() - a.getY()) * (c.getX() - b.getX()) - (b.getX() - a.getX()) * (c.getY() - b.getY());
        if (val == 0) {
            return 0; //line are collinear
        } else if (val < 0) {
            return 2;    //anti-clockwise direction
        }
        return 1;    //clockwise direction
    }

    /**
     * isIntersecting - returns true if the lines intersect, false otherwise.
     *
     * @param other Line object.
     * @return returns true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //four direction for two lines and points of other line
        int dir1 = direction(this.start, this.end, other.start);
        int dir2 = direction(this.start, this.end, other.end);
        int dir3 = direction(other.start, other.end, this.start);
        int dir4 = direction(other.start, other.end, this.end);

        if ((dir1 != dir2 && dir3 != dir4)) {
            return true; //they are intersecting
        }
        if (dir1 == 0 && onLine(this, other.start)) { //when p2 of line2 are on the line1
            return true;
        }
        if (dir2 == 0 && onLine(this, other.end)) { //when p1 of line2 are on the line1
            return true;
        }
        if (dir3 == 0 && onLine(other, this.start)) { //when p2 of line1 are on the line2
            return true;
        }
        if (dir4 == 0 && onLine(other, this.end)) { //when p1 of line1 are on the line2
            return true;
        }
        return false;
    }

    /**
     * intersectionWith - return the intersection point if the lines intersect and null otherwise.
     *
     * @param other Line object.
     * @return Point object.
     */

    public Point intersectionWith(Line other) {
        if (other == null || this == null) {
            return null;
        }
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();

        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * other.start.getX() + b2 * other.start.getY();

        double delta = a1 * b2 - a2 * b1;
        return new Point((b2 * c1 - b1 * c2) / delta, (a1 * c2 - a2 * c1) / delta);
    }

    /**
     * equals - return true is the lines are equal, false otherwise.
     *
     * @param other Line object.
     * @return return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return start.equals(other.start) && end.equals(other.end);
    }

    //

    /**
     * closestIntersectionToStartOfLine.
     * @param rect - a Rectangle object.
     * @return If this line does not intersect with the rectangle, return null.
     *       Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> collide = rect.intersectionPoints(this);
        double distance = -1;
        Point saveP = null;
        if (collide.size() == 0) {
            return null;
        } else {
            for (Point p : collide) {
                if (distance == -1) {
                    distance = this.start.distance(p);
                    saveP = p;
                }
                if (distance > this.start.distance(p)) {
                    distance = this.start.distance(p);
                    saveP = p;
                }
            }
            return saveP;
        }

    }
}
