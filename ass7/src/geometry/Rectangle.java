package geometry;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Tom Sendrovich/
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point downLeft;
    private Point downRight;

    private double width;
    private double height;

    /**
     * Constructor.
     *
     * @param upperLeft - the location of the upper left point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.upperRight = new Point((int) Math.round(upperLeft.getX() + getWidth()),
                (int) Math.round(upperLeft.getY()));
        this.downLeft = new Point((int) Math.round(upperLeft.getX()),
                (int) Math.round(upperLeft.getY() + getHeight()));
        this.downRight = new Point((int) Math.round(upperLeft.getX() + getWidth()), (int) Math.round(upperLeft.getY()
                + getHeight()));

    }

    /**
     * Constructor.
     */
    public Rectangle() {
    }


    /**
     * @param line - a Line object.
     * @return Return a (possibly empty) List of intersection points with the specified line.
     */

    public List<Point> intersectionPoints(Line line) {
        List<Point> collidables = new ArrayList<>();
        Line tempLine;
        int i = 0;
        tempLine = new Line(this.upperLeft, this.upperRight);
        if (tempLine.isIntersecting(line)) {
            collidables.add(i, line.intersectionWith(new Line(this.upperLeft, this.upperRight)));
            i++;
        }
        tempLine = new Line(this.upperLeft, this.downLeft);
        if (line.isIntersecting(tempLine)) {
            collidables.add(i, line.intersectionWith(new Line(this.upperLeft, this.downLeft)));
            i++;
        }
        tempLine = new Line(this.downRight, this.downLeft);
        if (line.isIntersecting(tempLine)) {
            collidables.add(i, line.intersectionWith(new Line(this.downRight, this.downLeft)));
            i++;
        }
        tempLine = new Line(this.downRight, this.upperRight);
        if (line.isIntersecting(tempLine)) {
            collidables.add(i, line.intersectionWith(new Line(this.downRight, this.upperRight)));
            i++;
        }
        return collidables;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return a Line object that is the top border of the rectangle.
     */
    public Line getTopBorder() {
        return new Line(this.upperLeft, this.upperRight);
    }

    /**
     * @return a Line object that is the bottom border of the rectangle.
     */
    public Line getBottomBorder() {
        return new Line(this.downLeft, this.downRight);

    }

    /**
     * @return a Line object that is the left border of the rectangle.
     */
    public Line getLeftBorder() {
        return new Line(this.upperLeft, this.downLeft);

    }

    /**
     * @return a Line object that is the right border of the rectangle.
     */
    public Line getRightBorder() {
        return new Line(this.upperRight, this.downRight);
    }

    /**
     * setX - set the x parameter of the upper-left point.
     *
     * @param newX - the new x parameter of the rectangle.
     */
    public void setX(double newX) {
        this.upperLeft = new Point(newX, this.upperLeft.getY());
    }
}


