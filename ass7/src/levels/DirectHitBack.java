package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class DirectHitBack extends Rectangle implements Sprite {
    /**
     * Constructor.
     */
    public DirectHitBack() {
        super();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 40, 800, 600);
        d.setColor(Color.BLUE);
        d.drawLine(260, 181, 380, 181);
        d.drawLine(541, 181, 419, 181);
        d.drawLine(400, 200, 400, 327);
        d.drawLine(400, 56, 400, 180);
        d.drawCircle(400, 181, 60);
        d.drawCircle(400, 181, 90);
        d.drawCircle(400, 181, 120);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
