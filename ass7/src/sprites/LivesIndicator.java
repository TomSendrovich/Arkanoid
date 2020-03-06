package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import removal.Counter;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Constructor.
     *
     * @param lives - a Count object.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 40);
        d.setColor(Color.black);
        d.drawText(15, 33, "Lives:" + lives.getValue(), 30);
        d.drawLine(120, 0, 120, 40);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
