package collidable;

/**
 * @author Tom Sendrovich.
 */
public class HitPointDecorator extends BlockCreatorDecorator {
    private int hitPoints;

    /**
     * Constructor.
     * @param decorated - a BlockCreator object.
     * @param value - a String object.
     */
    public HitPointDecorator(BlockCreator decorated, String value) {
        super(decorated);
        hitPoints = Integer.parseInt(value);
    }
    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setNumHits(hitPoints);
        return b;
    }
}
