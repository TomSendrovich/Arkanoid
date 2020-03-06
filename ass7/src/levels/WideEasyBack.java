package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class WideEasyBack extends Rectangle implements Sprite {
    /**
     * Constructor.
     */
    public WideEasyBack() {
        super();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 40, 800, 600);
        d.setColor(new Color(239, 231, 176));
        for (int i = 0; i < 130; i++) {
            d.drawLine(150, 180, i * 5, 270);
        }

        d.setColor(new Color(239, 231, 176));
        d.fillCircle(150, 180, 50);
        d.setColor(new Color(236, 215, 73));
        d.fillCircle(150, 180, 40);
        d.setColor(new Color(255, 225, 24));
        d.fillCircle(150, 180, 30);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
