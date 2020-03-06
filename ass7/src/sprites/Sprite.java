package sprites;

import game.GameLevel;
import biuoop.DrawSurface;

/**
 * @author Tom Sendrovich.
 */
public interface Sprite {
    /**
     * drawOn - draw the sprite to the screen.
     * @param d - a DrawSurface object.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed - notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * addToGame - add the sprite to the game.
     * @param g - a game object.
     */
    void addToGame(GameLevel g);
}
