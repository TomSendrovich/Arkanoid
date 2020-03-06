package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class OneColorBackground implements Sprite {
    private Color color;

    /**
     * Constructor.
     * @param color - a Color object.
     */
    public OneColorBackground(Color color) {
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
