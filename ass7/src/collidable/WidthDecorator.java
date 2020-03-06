package collidable;

/**
 * @author Tom Sendrovich.
 */
public class WidthDecorator extends BlockCreatorDecorator {
    private int width;

    /**
     * Constructor.
     * @param decorated - a BlockCreator object.
     * @param value - a String object.
     */
    public WidthDecorator(BlockCreator decorated, String value) {
        super(decorated);
        width = Integer.parseInt(value);
    }
    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setWidth(width);
        return b;
    }
}
