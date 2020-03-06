package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import removal.Counter;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor.
     *
     * @param score - a Counter object.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(135, 33, "Score:" + score.getValue(), 30);
        d.drawLine(290, 0, 290, 40);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
