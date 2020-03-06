package collidable;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * @author Tom Sendrovich.
 */
public class DrawingDecorator extends BlockCreatorDecorator {
    private boolean isFill;
    private Integer hitPoints;
    private Block.Drawer drawer;

    /**
     * Constructor.
     * @param decorated - a BlockCreator object.
     * @param value - a String object.
     * @param isFill - a bloean object.
     * @param hitPoints - an Integer object.
     */
    public DrawingDecorator(BlockCreator decorated, String value, boolean isFill, Integer hitPoints) {
        super(decorated);
        this.hitPoints = hitPoints;
        this.isFill = isFill;
        drawer = parseDrawer(value, isFill);
    }

    /**
     * parseDrawer - decides which Block.Drawer to create according to the value.
     * @param value - a String.
     * @param isfill - a boolean.
     * @return Block.Drawer object.
     */
    private Block.Drawer parseDrawer(String value, boolean isfill) {
        Block.Drawer result = null;
        if ((value.startsWith("color(RGB(")) && (value.endsWith("))"))) {
            String param = extractParameter(value, "color(RGB(", "))");
            String[] parts = param.split(",");
            int r = Integer.parseInt(parts[0].trim());
            int g = Integer.parseInt(parts[1].trim());
            int b = Integer.parseInt(parts[2].trim());
            Color color = new Color(r, g, b);
            if (isfill) {
                result = new Block.FillDrawer(color);
            } else {
                result = new Block.StrokeDrawer(color);
            }
        } else if ((value.startsWith("color(")) && (value.endsWith(")"))) {
            String param = extractParameter(value, "color(", ")");
            Color color;
            try {
                Field field = Color.class.getField(param);
                color = (Color) field.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Color name not supported: " + param);
            }

            if (isfill) {
                result = new Block.FillDrawer(color);
            } else {
                result = new Block.StrokeDrawer(color);
            }
        } else {
            if ((value.startsWith("image(")) && (value.endsWith(")"))) {
                String param = extractParameter(value, "image(", ")");

                if (!isfill) {
                    throw new RuntimeException("Image type not supported");
                }

                InputStream is = null;
                try {
                    is = ClassLoader.getSystemClassLoader().getResourceAsStream(param);
                    BufferedImage image = ImageIO.read(is);
                    result = new Block.ImageDrawer(image);
                } catch (IOException e) {
                    throw new RuntimeException("Failed loading image: " + param, e);
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            throw new RuntimeException("Failed loading image: " + param, e);
                        }
                    }
                }
            }

            // throw new RuntimeException("Unsupported definition: " + propValue);
        }

        return result;
    }

    /**
     * extractParameter - extract a specific part of the value String.
     * @param value - the extracted String object.
     * @param startsWith - String object.
     * @param endsWith String object.
     * @return extract a specific part of the value String.
     */
    private String extractParameter(String value, String startsWith, String endsWith) {
        return value.substring(startsWith.length(), value.length() - endsWith.length());
    }
    @Override
    public Block create(int x, int y) {
        Block block = super.create(x, y);
        if (isFill) {
            if (hitPoints == null) {
                block.setDefaultFillDrawer(drawer);
            } else {
                block.addFillDrawer(hitPoints, drawer);
            }
        } else if (hitPoints == null) {
            block.setDefaultStrokeDrawer(drawer);
        } else {
            block.addStrokeDrawer(hitPoints, drawer);
        }
        return block;
    }
}
