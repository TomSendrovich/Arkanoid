package collidable;

/**
 * @author Tom Sendrovich.
 */
public class BlockCreatorDecorator implements  BlockCreator {
    private BlockCreator decorated;

    /**
     * Constructor.
     * @param decorated - a BlockCreator object.
     */
    public BlockCreatorDecorator(BlockCreator decorated) {
        this.decorated = decorated;
    }
    @Override
    public Block create(int x, int y) {
        return decorated.create(x, y);
    }
}
