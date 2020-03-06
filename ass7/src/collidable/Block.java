package collidable;

import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import listener.HitListener;
import listener.HitNotifier;
import sprites.Ball;
import sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tom Sendrovich.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners = new ArrayList<>();
    // private int numHits;
    private Rectangle rectangle;
    private Drawer defaultStrokeDrawer;
    private Map<Integer, Drawer> strokeDrawers;
    private Drawer defaultFillDrawer;
    private Map<Integer, Drawer> fillDrawers;
    private int numHits;

    /**
     * Constructor.
     *
     * @param rectangle - holds a rectangle object.
     * @param color     - the color of the block.
     * @param numHits   - the number of hits till its "dead".
     */
    public Block(Rectangle rectangle, java.awt.Color color, int numHits) {
        this(rectangle.getUpperLeft().getX(), rectangle.getUpperLeft().getY());
        setWidth(rectangle.getWidth());
        setHeight(rectangle.getHeight());
        setNumHits(this.numHits);

        defaultFillDrawer = new FillDrawer(color);
        defaultStrokeDrawer = new StrokeDrawer(color);
    }

    /**
     * Constructor.
     *
     * @param x - x coordinate.
     * @param y - y coordinate.
     */
    public Block(double x, double y) {
        rectangle = new Rectangle(new Point(x, y), 1.0D, 1.0D);
        numHits = 1;

        strokeDrawers = new HashMap();
        fillDrawers = new HashMap();
        defaultFillDrawer = new NullDrawer();
        defaultStrokeDrawer = new NullDrawer();

    }

    /**
     * addStrokeDrawer - adds a strokeDrawer.
     *
     * @param hitPoints - the current hit points.
     * @param d         - a Drawer Object.
     */
    public void addStrokeDrawer(int hitPoints, Drawer d) {
        strokeDrawers.put(Integer.valueOf(hitPoints), d);
    }

    /**
     * addFillDrawer - add the specific fill for each hit point.
     *
     * @param hitPoints - the current hit points.
     * @param d         - a Drawer Object.
     */
    public void addFillDrawer(int hitPoints, Drawer d) {
        fillDrawers.put(Integer.valueOf(hitPoints), d);
    }

    /**
     * setDefaultStrokeDrawer - set a default stroke drawer.
     *
     * @param d - a Drawer Object.
     */
    public void setDefaultStrokeDrawer(Drawer d) {
        defaultStrokeDrawer = d;
    }

    /**
     * setWidth - set the blocks's width.
     *
     * @param width - the given width.
     */
    public void setWidth(double width) {
        rectangle = new Rectangle(new Point(rectangle.getUpperLeft().getX(), rectangle.getUpperLeft().getY()),
                width, rectangle.getHeight());
    }

    /**
     * setHeight - set the block's height.
     *
     * @param height - the given height.
     */
    public void setHeight(double height) {
        rectangle = new Rectangle(new Point(rectangle.getUpperLeft().getX(), rectangle.getUpperLeft().getY()),
                rectangle.getWidth(), height);
    }

    /**
     * @return the rectangle object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * drawOn - draw the block, with its number of hits to the GUI.
     *
     * @param surface - DraeSurface object.
     */
    public void drawOn(DrawSurface surface) {
        if (fillDrawers.containsKey(Integer.valueOf(numHits))) {
            (fillDrawers.get(Integer.valueOf(numHits))).drawAt(surface, rectangle);
        } else {
            defaultFillDrawer.drawAt(surface, rectangle);
        }
        if (strokeDrawers.containsKey(Integer.valueOf(numHits))) {
            (strokeDrawers.get(Integer.valueOf(numHits))).drawAt(surface, rectangle);
        } else {
            defaultStrokeDrawer.drawAt(surface, rectangle);
        }
    }

    /**
     * drawTextOn - draw the number of hits of each block on it.
     *
     * @param surface - DraeSurface object.
     */
    public void drawTextOn(DrawSurface surface) {
        if (numHits > 0) {
            surface.drawText((int) (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 2),
                    (int) (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight() / 2),
                    String.valueOf(this.numHits), 20);
        } else {
            surface.drawText((int) (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 2),
                    (int) (this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight() / 2), "x", 20);
        }

    }

    /**
     * timePassed - an empty method for now.
     */
    @Override
    public void timePassed() {

    }

    /**
     * addToGame - add the block to both collidables list and sprites list.
     *
     * @param g - holds a list of collidables and a list of sprites.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * hit - Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  - the point of collision of the ball with the block.
     * @param currentVelocity - the current Volocity of the ball.
     * @param hitter          - the current ball.
     * @return The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        numHits--;
        this.notifyHit(hitter);
        if (this.getCollisionRectangle().getTopBorder().onLine(this.getCollisionRectangle().getTopBorder(),
                collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }

        if (this.getCollisionRectangle().getBottomBorder().onLine(this.getCollisionRectangle().getBottomBorder(),
                collisionPoint)) {
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
     * removeFromGame - remove a Block from both collidables and sprites list.
     *
     * @param gameLevel - holds a list of collidables and a list of sprites.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * notifyHit - notify all hitListeners about a hit event.
     *
     * @param hitter - the ball that hit the Block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all hitListeners about a hit event:
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * @return the number of hit points of the block.
     */
    public int getNumHits() {
        return numHits;
    }

    /**
     * setNumHits - set the block's number of hits.
     * @param hits - the given number of hits.
     */
    public void setNumHits(int hits) {
        this.numHits = hits;
    }

    /**
     * setDefaultFillDrawer - sets a default drawer to the blocks.
     * @param d - a Drawer Object
     */
    public void setDefaultFillDrawer(Drawer d) {
        defaultFillDrawer = d;
    }

    /**
     * Drawer - anonymous interface.
     */
    public interface Drawer {
        /**
         * drawAt - draws the block.
         * @param surface - a DrawSurface Object.
         * @param rectangle - a Rectangle Object.
         */
        void drawAt(DrawSurface surface, Rectangle rectangle);
    }

    /**
     * FillDrawer - Draws a block withe a certain color.
     */
    public static class FillDrawer implements Block.Drawer {
        private Color color;

        /**
         * Constructor.
         * @param color - a Color Object.
         */
        public FillDrawer(Color color) {
            this.color = color;
        }
        @Override
        public void drawAt(DrawSurface surface, Rectangle rectangle) {
            surface.setColor(color);
            surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                    (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
    }

    /**
     * StrokeDrawer.
     */
    public static class StrokeDrawer implements Block.Drawer {
        private Color color;

        /**
         * Constructor.
         * @param color - a Color Object.
         */
        public StrokeDrawer(Color color) {
            this.color = color;
        }
        @Override
        public void drawAt(DrawSurface surface, Rectangle rectangle) {
            surface.setColor(color);
            surface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                    (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
    }

    /**
     * ImageDrawer - draws a block with images inside it.
     */
    public static class ImageDrawer implements Block.Drawer {
        private Image image;

        /**
         * Constructor.
         * @param image - an Image object.
         */
        public ImageDrawer(Image image) {
            this.image = image;
        }
        @Override
        public void drawAt(DrawSurface surface, Rectangle rectangle) {
            surface.drawImage((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(), image);
        }
    }

    /**
     * NullDrawer - a default empty drawer.
     */
     private final class NullDrawer implements Block.Drawer {
        /**
         * Constructor.
         */
        private NullDrawer() {
        }
        @Override
        public void drawAt(DrawSurface surface, Rectangle rectangle1) {
        }
    }
}
