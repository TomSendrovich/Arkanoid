package collidable;

/**
 * @author Tom Sendrovich.
 */
public class HeightDecorator extends BlockCreatorDecorator {
    private int height;

    /**
     * Constructor.
     * @param decorated - a BlockCreator object.
     * @param value - a String object.
     */
    public HeightDecorator(BlockCreator decorated, String value) {
        super(decorated);
        height = Integer.parseInt(value);
    }
    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setHeight(height);
        return b;
    }
}
