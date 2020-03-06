package collidable;

/**
 * @author Tom Sendrovich
 */
public class DecoratorsFactory {
    /**
     * decorate - creates one of the following decorators: hitPoint, height, width, drawing.
     * @param blockCreator - BlockCreator object.
     * @param key - a String object.
     * @param value - a String value.
     * @return onr of the following decorators: hitPoint, height, width, drawing.
     */
    public BlockCreator decorate(BlockCreator blockCreator, String key, String value) {
        if ("hit_points".equals(key)) {
            return new HitPointDecorator(blockCreator, value);
        }
        if ("height".equals(key)) {
            return new HeightDecorator(blockCreator, value);
        }
        if ("width".equals(key)) {
            return new WidthDecorator(blockCreator, value);
        }
        if ((key.startsWith("fill")) || (key.startsWith("stroke"))) {
            Integer hitPoints = null;
            boolean isFill = key.startsWith("fill");
            int divideI = key.indexOf("-");
            if (divideI != -1) {
                hitPoints = Integer.parseInt(key.substring(divideI + 1));
            }

            return new DrawingDecorator(blockCreator, value, isFill, hitPoints);
        }
        throw new RuntimeException("Unsupported property: " + key + " with value:" + value);
    }
}

