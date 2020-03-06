package animation;

/**
 * @author Tom Sendrovich.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * Constructor.
     * @param runner - an AnimationRunner object.
     * @param highScoresAnimation - an Animation object.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    @Override
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
