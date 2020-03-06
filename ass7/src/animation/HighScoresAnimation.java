package animation;

import biuoop.DrawSurface;
import scores.HighScoresTable;
import scores.ScoreInfo;

import java.awt.Color;
import java.util.List;

/**
 * @author Tom Sendrovich..
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scoresTable;
    private AnimationRunner runner;

    /**
     * Constructor.
     * @param scores - a HighScoresTable object.
     * @param runner - an AnimationRuneer object.
     */
    public HighScoresAnimation(HighScoresTable scores, AnimationRunner runner) {
        this.scoresTable = scores;
        this.runner = runner;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(100, 100, "High Scores:", 62);
        d.drawLine(0, 110, 800, 110);
        d.drawLine(0, 112, 800, 112);
        d.drawLine(0, 114, 800, 114);
        List<ScoreInfo> scoreInfos = scoresTable.getHighScores();
        int i = 40;
        for (ScoreInfo s : scoreInfos) {
            d.drawText(55, (d.getHeight() / 5) + i, s.getName() + " :" + s.getScore(), 35);
            i += 70;

        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

}
