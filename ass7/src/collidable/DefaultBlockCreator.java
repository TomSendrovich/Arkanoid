package collidable;

/**
 * @author Tom Sendrovich.
 */
public class DefaultBlockCreator implements BlockCreator {
    /**
     * Constructor.
     */
    public DefaultBlockCreator() {
    }

    @Override
    public Block create(int xpos, int ypos) {
        return new Block(xpos, ypos);
    }
}
