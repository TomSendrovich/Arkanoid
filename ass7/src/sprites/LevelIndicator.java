package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class LevelIndicator implements Sprite {
    private String currentLevel;

    /**
     * Constructor.
     *
     * @param currentLevel - a String with tha level.txt's name.
     */
    public LevelIndicator(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(300, 33, "Level Name: " + currentLevel, 30);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
