package levels;

import collidable.Block;
import collidable.Velocity;
import sprites.ImageBackground;
import sprites.OneColorBackground;
import sprites.Sprite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static javax.imageio.ImageIO.read;

/**
 * @author Tom Sendrovich.
 */
public class LevelSpecificationReader implements LevelInformation {
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;
    private Sprite backgroundSprite;
    private int blocksStartX;
    private int blocksStartY;
    private int rowHeight;
    private int numBlocks;

    /**
     * Constructor.
     */
    public LevelSpecificationReader() {
        velocities = new ArrayList<>();
        blocks = new ArrayList<>();
    }

    /**
     * fromReader - reads the file and builds the list of levels.
     *
     * @param reader - a Reader object.
     * @return a list of levelInformation.
     */
    public List<LevelInformation> fromReader(Reader reader) {
        BlocksFromSymbolsFactory currentLevelBlockDefinitions = null;
        Integer currentBlocksStartY = null;
        Integer currentBlocksStartX = null;
        Integer currentRowHeight = null;
        LevelSpecificationReader currentLevel = null;
        List<LevelInformation> levelInformations = new ArrayList<>();
        String line;
        try {
            BufferedReader lineReader = new BufferedReader(reader);
            while ((line = lineReader.readLine()) != null) {
                line = line.trim();
                if ((!"".equals(line)) && (!line.startsWith("#"))) {
                    if (line.equals("START_LEVEL")) {
                        currentLevel = new LevelSpecificationReader();
                    } else if (line.equals("END_LEVEL")) {
                        levelInformations.add(currentLevel);
                        currentLevel = null;
                        currentBlocksStartY = null;
                        currentBlocksStartX = null;
                        currentRowHeight = null;
                        currentLevelBlockDefinitions = null;
                    } else {
                        if ("START_BLOCKS".equals(line)) {
                            int currentX;
                            int currentY = currentBlocksStartY;
                            int j = 1, currentBlocks = 0;
                            line = lineReader.readLine();
                            while (!(line).equals("END_BLOCKS")) {
                                currentX = currentBlocksStartX;
                                for (int i = 0; i < line.length(); i++) {
                                    String symbol = line.substring(i, i + 1);
                                    if (currentLevelBlockDefinitions.isSpaceSymbol(symbol)) {
                                        currentX += currentLevelBlockDefinitions.getSpaceWidth(symbol);
                                    } else {
                                        Block b = currentLevelBlockDefinitions.createBlock(symbol, currentX, currentY);
                                        if (b == null) {
                                            throw new RuntimeException("Failed creating block of type:" + symbol);
                                        }
                                        currentX += b.getCollisionRectangle().getWidth();
                                        currentLevel.addBlock(b);
                                    }
                                }
                                currentY += (j) * currentRowHeight;
                                if (currentBlocks != blocks.size()) {
                                    j++;
                                    currentBlocks = blocks.size();
                                }
                                line = lineReader.readLine();
                            }
                        }
                        if (!line.equals("END_BLOCKS")) {
                            String[] split = line.split(":");
                            String key = split[0];
                            String value = split[1];
                            if ("level_name".equals(key)) {
                                levelName = value;
                                currentLevel.setLevelName(value);
                            } else if ("ball_velocities".equals(key)) {
                                currentLevel.velocities = splitVelocities(value);
                            } else if ("background".equals(key)) {
                                determineBackground(currentLevel, value);
                            } else if ("paddle_speed".equals(key)) {
                                currentLevel.setPaddleSpeed(Integer.valueOf(value));
                                currentLevel.paddleSpeed = Integer.valueOf(value);
                            } else if ("paddle_width".equals(key)) {
                                currentLevel.setPaddleWidth(Integer.valueOf(value));
                                currentLevel.paddleWidth = Integer.valueOf(value);
                            } else if ("block_definitions".equals(key)) {
                                currentLevelBlockDefinitions = BlocksDefinitionReader.fromReader(
                                        new FileReader("resources/" + value));
                            } else if ("blocks_start_x".equals(key)) {
                                currentBlocksStartX = Integer.valueOf(value);
                                currentLevel.startX(Integer.valueOf(value));
                            } else if ("blocks_start_y".equals(key)) {
                                currentBlocksStartY = Integer.valueOf(value);
                                currentLevel.startY(Integer.parseInt(value));
                            } else if ("row_height".equals(key)) {
                                currentRowHeight = Integer.parseInt(value);
                                currentLevel.setRowHeight(Integer.parseInt(value));
                            } else if ("num_blocks".equals(key)) {
                                currentLevel.numBlocks = Integer.valueOf(value);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not find the file");
        }
        return levelInformations;
    }

    /**
     * setStartX - set the x coordinate of the start of the blocks.
     *
     * @param startX - x coordinate.
     */
    private void startX(Integer startX) {
        this.blocksStartX = startX;
    }

    /**
     * setStartY - set the x coordinate of the start of the blocks.
     *
     * @param startY - Y coordinate.
     */
    private void startY(Integer startY) {
        this.blocksStartY = startY;
    }

    /**
     * setRowHeight - set the row height.
     *
     * @param height - the height of the first row.
     */
    private void setRowHeight(Integer height) {
        this.rowHeight = height;
    }

    /**
     * splitVelocities - split the value String to speed and angle.
     *
     * @param value - a String object.
     * @return a velocities list.
     */
    public List<Velocity> splitVelocities(String value) {
        List<Velocity> velocityList = new ArrayList<>();
        int angle, speed;
        String[] split = value.split(" ");
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split(",");
            speed = Integer.valueOf(split1[0]);
            angle = Integer.valueOf(split1[1]);
            velocityList.add(Velocity.fromAngleAndSpeed(angle, speed));
        }
        return velocityList;
    }

    /**
     * determineBackground - determines the correct form of background.
     *
     * @param currentLevel - LevelSpecificationReader object.
     * @param value        - a String object.
     */
    public void determineBackground(LevelSpecificationReader currentLevel, String value) {
        if ((value.startsWith("color(RGB("))) {
            String colorParam = value.substring(11, value.length() - 2);
            String[] parts = colorParam.split(",");
            int r = Integer.parseInt(parts[0].trim());
            int g = Integer.parseInt(parts[1].trim());
            int b = Integer.parseInt(parts[2].trim());
            Color color = new Color(r, g, b);
            currentLevel.setBackgroundSprite(new OneColorBackground(color));
        } else if ((value.startsWith("color(")) && (value.endsWith(")"))) {
            String colorParam = value.substring(6, value.length() - 1);
            try {
                Field field = Color.class.getField(colorParam);
                Color color = (Color) field.get(null);
                currentLevel.setBackgroundSprite(new OneColorBackground(color));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(colorParam + " is not a color name");
            }
        } else if ((value.startsWith("image(")) && (value.endsWith(")"))) {
            String imageParam = value.substring(6, value.length() - 1);
            InputStream is = null;
            try {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(imageParam);
                BufferedImage image = read(is);
                currentLevel.setBackgroundSprite(new ImageBackground(image));
                currentLevel.backgroundSprite = new ImageBackground(image);
            } catch (IOException e) {
                throw new RuntimeException("Failed loading image: " + imageParam, e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        throw new RuntimeException("Failed loading image: " + imageParam, e);
                    }

                }
            }
        }
    }

    @Override
    public int numberOfBalls() {
        return velocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.backgroundSprite;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }

    /**
     * setBackgroundSprite - set the backgroundSprite field.
     *
     * @param sprite - a Sprite object.
     */
    public void setBackgroundSprite(Sprite sprite) {
        this.backgroundSprite = sprite;
    }

    /**
     * add a block to the blocks list.
     *
     * @param block - a block object.
     */
    public void addBlock(Block block) {
        blocks.add(block);
    }


    /**
     * set the paddle's speed.
     *
     * @param speed - the speed.
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * set the paddle's width.
     *
     * @param width - the width.
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * set the level's name.
     *
     * @param name - a String object.
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }
}
