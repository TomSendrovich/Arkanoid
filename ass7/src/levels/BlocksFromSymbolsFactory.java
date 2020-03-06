package levels;

import collidable.Block;
import collidable.BlockCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tom Sendrovich.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor.
     */
    public BlocksFromSymbolsFactory() {
        spacerWidths = new HashMap<>();
        blockCreators = new HashMap<>();
    }

    /**
     * isSpaceSymbol - returns true if 's' is a valid space symbol.
     * @param s - a String object.
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * is BlockSymbol -  returns true if 's' is a valid block symbol.
     * @param s - a StringObject.
     * @return returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * getBlock - Return a block according to the definitions associated
     *      with symbol s. The block will be located at position (xpos, ypos).
     * @param s - a String object.
     * @param xpos - x coordinate.
     * @param ypos - y coordinate.
     * @return a block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * getSpaceWidth - Returns the width in pixels associated with the given spacer-symbol.
     * @param s - a String object.
     * @return - Returns the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * addBlockCreator - adds a symbol and a block to the blockCreator map.
     * @param symbol - a String object.
     * @param creator - a BlockCreator Object.
     */
    public void addBlockCreator(String symbol, BlockCreator creator) {
        blockCreators.put(symbol, creator);
    }

    /**
     * addSpacer - adds a symbol and its width to the spacerWidths map.
     * @param symbol - a String object.
     * @param width - the width of the spacer.
     */
    public void addSpacer(String symbol, int width) {
        spacerWidths.put(String.valueOf(symbol), Integer.valueOf(width));
    }

    /**
     * createBlock - creates a block through a certain symbol.
     * @param symbol - a String object.
     * @param x - x coordinate.
     * @param y - y coordinate.
     * @return a block if symbol is in blockCreators, else return null.
     */
    public Block createBlock(String symbol, int x, int y) {
        if (blockCreators.containsKey(String.valueOf(symbol))) {
            return (blockCreators.get(String.valueOf(symbol))).create(x, y);
        }
        return null;
    }
}
