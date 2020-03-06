package levels;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Sendrovich.
 */
public class LevelSet {
    private List<LevelSet> levelSetList;
    private String message;
    private String key;
    private String levelPath;

    /**
     * Constructor.
     */
    public LevelSet() {
        this.levelSetList = new ArrayList<>();
    }

    /**
     * LevelSet - return a level set list.
     * @param reader - a Reader object.
     * @return return a level set list.
     */
    public static LevelSet fromReader(Reader reader) {
        LevelSet levelSet = new LevelSet();
        LevelSet currentSet = null;
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        String line;
        try {
            while ((line = lineNumberReader.readLine()) != null) {
                if (lineNumberReader.getLineNumber() % 2 == 0) {
                    currentSet.setLevelPath(line.trim());
                    levelSet.addLevelSet(currentSet);
                    currentSet = null;
                } else {
                    currentSet = new LevelSet();
                    String[] lineParts = line.trim().split(":");
                    currentSet.setKey(lineParts[0]);
                    currentSet.setMessage(lineParts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not find a a level set file");
        }
        return levelSet;
    }

    /**
     * addLevelSet - adds to the levelSetList.
     * @param levelSet - a LevelSet object.
     */
    public void addLevelSet(LevelSet levelSet) {
        levelSetList.add(levelSet);
    }

    /**
     *
     * @return return the levelSetList.
     */
    public List<LevelSet> getLevelSetList() {
        return levelSetList;
    }

    /**
     *
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * set the message field.
     * @param messageStr - a String object.
     */
    public void setMessage(String messageStr) {
        this.message = messageStr;
    }

    /**
     *
     * @return the key field.
     */
    public String getKey() {
        return key;
    }

    /**
     * set the key field.
     * @param keyStr - a String object.
     */
    public void setKey(String keyStr) {
        this.key = keyStr;
    }

    /**
     *
     * @return levelPath field.
     */
    public String getLevelPath() {
        return levelPath;
    }

    /**
     * set the levelPath field.
     * @param levelPathStr - a String object.
     */
    public void setLevelPath(String levelPathStr) {
        this.levelPath = levelPathStr;
    }
}
