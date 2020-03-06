package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Sendrovich.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * addSprite - add the Sprite object s to the sprites ArrayList.
     *
     * @param s - a sprite Object.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * remove s from the sprites list.
     * @param s - a Sprite object.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * removes all the sprites from the sprites list.
     */
    public void removeAllSprites() {
        int size = sprites.size();
        for (int i = 0; i < size; i++) {
            sprites.remove(0);
        }
    }

    /**
     * notifyAllTimePassed - call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * drawAllOn - call drawOn(d) on all sprites.
     *
     * @param d - a DrawSurface object.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }


}
