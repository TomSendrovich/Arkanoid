package collidable;

/**
 * @author Tom Sendrovich.
 */
public interface BlockCreator {
    /**
     * create - returns a blocks that the top left point is (x,y).
     * @param xpos - x coordinate.
     * @param ypos - y coordinate.
     * @return - a block.
     */
    Block create(int xpos, int ypos);
}
