package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class User {
    private static final String SCORE_FILE_NAME = "rating.txt";
    private final String name;
    private int score;

    User(String name) {
        this.name = name;
        loadScore();

    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    /**
     * Create file object using a possible location on the disk
     * @return File object
     */
    private File readFile() {
        return new File(SCORE_FILE_NAME);
    }

    private void loadScore() {
        try {
            setScoreFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
            this.score = 0;
        }
    }

    /**
     * Try to load the User score from file, if it exists, if not set default to 0
     * @throws FileNotFoundException If file not found, throw exception and set default
     */
    private void setScoreFromFile() throws FileNotFoundException {
        try (Scanner sc = new Scanner(readFile())) {
            while (sc.hasNext()) {
                String[] userData = sc.nextLine().split(" ");
                String playerName = userData[0];
                int playerScore = Integer.parseInt(userData[1]);

                if (this.getName().equals(playerName)) {
                    this.setScore(playerScore);
                    return;
                }
            }
        }
        this.score = 0;
    }

    /**
     * Increase score
     * @param score amount to increase the score with
     */
    public void increaseScore(int score) {
        this.score += score;
    }
}
