package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class Green3Back extends Rectangle implements Sprite {
    /**
     * Constructor.
     */
    public Green3Back() {
        super();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(42, 130, 21));
        d.fillRectangle(0, 40, 800, 600);
        d.setColor(new Color(46, 42, 41));
        d.fillRectangle(100, 451, 80, 600);
        d.setColor(new Color(255, 255, 255));
        int y = 456, x = 105;
        for (int i = 0; i < 20; i++) {
            if (i % 5 == 0 && i != 0) {
                y += 36;
                x = 105;
            }
            d.fillRectangle(x, y, 10, 31);
            x += 15;
        }
        d.setColor(new Color(62, 58, 57));
        d.fillRectangle(128, 400, 24, 51);
        d.setColor(new Color(78, 74, 73));
        d.fillRectangle(135, 200, 10, 200);
        d.setColor(new Color(215, 171, 102));
        d.fillCircle(140, 210, 15);
        d.setColor(new Color(245, 77, 54));
        d.fillCircle(140, 210, 10);
        d.setColor(new Color(253, 253, 254));
        d.fillCircle(140, 210, 4);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
