package game;

import animation.AnimationRunner;
import animation.Menu;
import animation.MenuAnimation;
import animation.Task;
import animation.ShowHiScoresTask;
import animation.KeyPressStoppableAnimation;
import animation.HighScoresAnimation;
import animation.WinAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import levels.LevelSet;
import levels.LevelSpecificationReader;
import removal.Counter;
import scores.HighScoresTable;
import scores.ScoreInfo;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Tom Sendrovich.
 */
public class GameFlow {

    private List<LevelInformation> levelInformationList;
    private LevelSpecificationReader levelSpecificationReader;
    private Reader reader;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score = new Counter(0);
    private Counter lives = new Counter(7);
    private HighScoresTable scoresTable;
    private int size;

    /**
     * Constructor.
     *
     * @param ar - an AnimationRunner object.
     * @param ks - a KeyboardSensor object.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        scoresTable = new HighScoresTable(5);
        File file = new File("resources/highscore.txt");
        scoresTable = HighScoresTable.loadFromFile(file);
        size = scoresTable.getHighScores().size();
/*        } catch (FileNotFoundException e) {
            scoresTable = new HighScoresTable(5);
        } catch (IOException e) {
            scoresTable = new HighScoresTable(5);
        }*/
        try {
            this.levelSpecificationReader = new LevelSpecificationReader();
            reader = new FileReader("resources/level_definitions.txt");
            this.levelInformationList = levelSpecificationReader.fromReader(reader);
        } catch (IOException e) {
            throw new RuntimeException("Could not open a highscore file");
        }

    }

    /**
     * runGame - creates the menu, and acoording to the players choice do one of the tasks.
     *
     * @param levelsPath - the path of the level sets.
     */
    public void runGame(String levelsPath) {
        // ...
        while (true) {
            Menu<Task<Void>> menu = new MenuAnimation<>("Main Menu", keyboardSensor, animationRunner);
            Menu<Task<Void>> levelSetsMenu = new MenuAnimation<>("Level Sets", keyboardSensor, animationRunner);
            LevelSet levelSet;
            if (levelsPath == null) {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
                levelSet = LevelSet.fromReader(new InputStreamReader(is));
            } else {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelsPath);
                levelSet = LevelSet.fromReader(new InputStreamReader(is));
            }
            for (final LevelSet currentSet : levelSet.getLevelSetList()) {
                levelSetsMenu.addSelection(currentSet.getKey(), currentSet.getMessage(), new Task<Void>() {
                    @Override
                    public Void run() {

                        List<LevelInformation> levelInformations = null;
                        LevelSpecificationReader levelReader = new LevelSpecificationReader();
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                                currentSet.getLevelPath());
                        levelInformations = levelReader.fromReader(new InputStreamReader(is));


                        runLevels(levelInformations);

                        return null;
                    }
                });
            }
            resetGame();
            menu.addSubMenu("s", "Start Game", levelSetsMenu);
            menu.addSelection("h", "High Scores",
                    new ShowHiScoresTask(animationRunner, new KeyPressStoppableAnimation(keyboardSensor,
                            KeyboardSensor.SPACE_KEY,
                            new HighScoresAnimation(scoresTable, animationRunner))));
            menu.addSelection("q", "Quit", new Task<Void>() {
                @Override
                public Void run() {
                    System.exit(1);
                    return null;
                }
            });
            animationRunner.run(menu);
            Task<Void> status = menu.getStatus();
            status.run();
        }
    }

    /**
     * runLevels - run all the levelInformationList List one by one.
     *
     * @param levels - a List of the levelInformationList.
     */

    public void runLevels(List<LevelInformation> levels) {


        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, animationRunner.getGui(), score, lives);

            level.initialize();

            while (level.numBlocks().getValue() != 0 && level.numLives().getValue() != 0) {
                level.playOneTurn();

            }
            this.score = level.numScore();
            this.lives = level.numLives();
            if (lives.getValue() == 0) {
                break;
            }
        }
        if (lives.getValue() != 0) {
            score.increase(100);
            lives.decrease(lives.getValue());
        }
        List<ScoreInfo> scoreInfos = scoresTable.getHighScores();
        if (scoreInfos.size() == 0) {
            DialogManager dialog = animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");
            scoresTable.add(new ScoreInfo(name, score.getValue()));
            try {
                scoresTable.save(new File("resources/highscore.txt"));
            } catch (IOException e) {
                throw new RuntimeException("Could not find a highscore file");
            }
        }
        if (score.getValue() > scoreInfos.get(scoreInfos.size() - 1).getScore()
                || scoreInfos.size() < scoresTable.size()) {
            if (size == scoresTable.getHighScores().size()) {
                DialogManager dialog = animationRunner.getGui().getDialogManager();
                String name = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");
                scoresTable.add(new ScoreInfo(name, score.getValue()));
                try {
                    scoresTable.save(new File("resources/highscore.txt"));
                } catch (IOException e) {
                    throw new RuntimeException("Could not open a highscore file");

                }
            }

        }

        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new WinAnimation(score)));
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(
                        scoresTable, animationRunner)));
        //this.animationRunner.getGui().close();
    }

    /**
     * resetGame - after a game is played, reset the lives, score and levels.
     */
    private void resetGame() {
        if (lives.getValue() == 0) {
            lives.increase(7);
            score.decrease(score.getValue());
        }
        try {
            this.levelSpecificationReader = new LevelSpecificationReader();
            reader = new FileReader("resources/level_definitions.txt");
            this.levelInformationList = levelSpecificationReader.fromReader(reader);
        } catch (IOException e) {
            throw new RuntimeException("Could not find a a level definitions file");

        }
    }
}
