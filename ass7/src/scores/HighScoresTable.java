package scores;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Sendrovich.
 */
public class HighScoresTable {
    private List<ScoreInfo> scoreInfos;
    private int size;

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.

    /**
     * Constructor - Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size - the size of the table.
     */
    public HighScoresTable(int size) {
        this.scoreInfos = new ArrayList<>(size);
        this.size = size;
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.

    /**
     * HighScoresTable - if the file does no exist, create an empty one, else reads the file.
     * @param filename - the file path.
     * @return if the file does no exist, create an empty one, else reads the file.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoresTable = new HighScoresTable(5);
        String line = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new
                    FileReader(filename));
        } catch (FileNotFoundException e) {
            return highScoresTable;
        }
        while (true) {
            try {
                line = bufferedReader.readLine();
                if ((line) == null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] parts = line.split(":");
            ScoreInfo s = new ScoreInfo(parts[0], Integer.valueOf(parts[1]));
            highScoresTable.scoreInfos.add(s);

        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScoresTable;
    }

    /**
     * sortTable - a BubbleSort algorithm.
     */
    public void sortTable() {
        boolean swapped = true;
        int j = 0;
        ScoreInfo tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < scoreInfos.size() - j; i++) {
                if (scoreInfos.get(i).getScore() < scoreInfos.get(i + 1).getScore()) {
                    tmp = scoreInfos.get(i);
                    scoreInfos.add(i, scoreInfos.get(i + 1));
                    scoreInfos.remove(i + 1);
                    scoreInfos.add(i + 1, tmp);
                    scoreInfos.remove(i + 2);
                    swapped = true;
                }
            }
        }

    }

    /**
     * Add a high-score.
     * @param score - scoreInfo object.
     */
    public void add(ScoreInfo score) {
        scoreInfos.add(score);
        sortTable();
        if (scoreInfos.size() > size) {
            scoreInfos.remove(size);
        }
    }

    /**
     *
     * @return Return table size.
     */
    public int size() {
        return this.size;
    }

    /**
     *
     * @return Return the current high scores. The list is sorted such that the highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        sortTable();
        return this.scoreInfos;
    }
    /**
     *
     * @param score - an int object.
     * @return return the rank of the current score:
     */
    public int getRank(int score) {
        for (int i = 1; i <= this.size; i++) {
            if (score >= scoreInfos.get(i).getScore()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scoreInfos.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     * @param filename - a filepath.
     * @throws IOException - if the file not found.
     */
    public void load(File filename) throws IOException {
        String line;
        BufferedReader bufferedReader = new BufferedReader(new
                FileReader(filename));
        if (bufferedReader == null) {
            throw new IOException("file not found");
        }
        line = bufferedReader.readLine();
        while (line != null && scoreInfos.size() < size) {
            String[] parts = line.split(":");
            ScoreInfo s = new ScoreInfo(parts[0], Integer.valueOf(parts[1]));
            scoreInfos.add(s);

        }
        bufferedReader.close();
    }

    /**
     * Save table data to the specified file.
     * @param filename - a filename path String.
     * @throws IOException - if the file not found.
     */
    public void save(File filename) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new
                FileWriter(filename));
        try {

            for (int i = 0; i < scoreInfos.size(); i++) {
                bufferedWriter.write(scoreInfos.get(i).getName() + ":" + scoreInfos.get(i).getScore() + "\n");
            }
        } catch (IOException e) {
            throw new IOException("Cannot find file");
        } finally {
            bufferedWriter.close();
        }
    }
}