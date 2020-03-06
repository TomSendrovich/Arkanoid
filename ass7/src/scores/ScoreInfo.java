package scores;

/**
 * @author Tom Sendrovich.
 */
public class ScoreInfo {
    private String name;
    private int score;

    /**
     * Constructor.
     * @param name - a String object.
     * @param score - the current score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     *
     * @return the name field.
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return the score field.
     */
    public int getScore() {
        return this.score;
    }


}
