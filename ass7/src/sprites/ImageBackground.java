package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Image;

/**
 * @author Tom Sendrovich.
 */
public class ImageBackground implements Sprite {
    private Image image;

    /**
     * Constructor.
     * @param image - an Image object.
     */
    public ImageBackground(Image image) {
        this.image = image;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, image);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
