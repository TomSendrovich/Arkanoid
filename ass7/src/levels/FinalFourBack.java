package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Tom Sendrovich.
 */
public class FinalFourBack extends Rectangle implements Sprite {
    /**
     * Constructor.
     */
    public FinalFourBack() {
        super();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(23, 135, 207));
        d.fillRectangle(0, 40, 800, 600);
        //left Cloud - circles from left to right
        d.setColor(Color.WHITE);
        int x = 130;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x, 430, x - 20, 600);
            x += 8;
        }
        d.setColor(new Color(203, 203, 203));
        d.fillCircle(130, 430, 20);
        d.fillCircle(145, 450, 25);
        d.setColor(new Color(186, 186, 186));
        d.fillCircle(160, 425, 25);
        d.setColor(new Color(169, 169, 169));
        d.fillCircle(190, 430, 28);
        d.fillCircle(180, 450, 20);

        //right cloud - circles from left to right
        d.setColor(Color.WHITE);
        int x1 = 630;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, 520, x1 - 30, 600);
            x1 += 8;
        }
        d.setColor(new Color(203, 203, 203));
        d.fillCircle(620, 520, 20);
        d.fillCircle(640, 535, 25);
        d.setColor(new Color(186, 186, 186));
        d.fillCircle(655, 517, 25);
        d.setColor(new Color(169, 169, 169));
        d.fillCircle(685, 530, 28);
        d.fillCircle(665, 540, 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
