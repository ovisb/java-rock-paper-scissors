package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class RockPaperScissors {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private static final int POINTS_WIN =  100;
    private static final int POINTS_DRAW = 50;
    private String getInput() {
        return scanner.next();
    }

    private static final String SCORE_FILE_NAME = "rating.txt";

    private boolean isInputValid(String userInput) {
        boolean isValid = false;

        try {
            GameChoices userChoice = GameChoices.valueOf(userInput.toUpperCase());

            for (GameChoices enumChoice: GameChoices.values()) {
                if (enumChoice == userChoice ) {
                    isValid = true;
                    break;
                }
            }
        } catch (IllegalArgumentException ignored) {
            System.out.println("Invalid input");
        }

        return isValid;
    }

    private String findWhoBeatsChoice(GameChoices choice) {
        return choice.getLosesAgainst();
    }

    private String getComputerChoice() {
        GameChoices[] availableChoices = GameChoices.values();
        int randomInt = random.nextInt(availableChoices.length);
        return availableChoices[randomInt].name().toLowerCase();
    }

    private void checkWinner(String userInput, User user) {
        String computerChoice = getComputerChoice();

        GameChoices userChoice = GameChoices.valueOf(userInput.toUpperCase());
        GameChoices compChoice = GameChoices.valueOf(computerChoice.toUpperCase());

        String whoBeatsUser = findWhoBeatsChoice(userChoice);
        String whoBeatsComp = findWhoBeatsChoice(compChoice);

        if (userInput.equals(whoBeatsComp)) {
            System.out.printf("Well done. The computer chose %s and failed%n", computerChoice);
            user.increaseScore(POINTS_WIN);
        } else if (computerChoice.equals(whoBeatsUser)) {
            System.out.printf("Sorry, but the computer chose %s%n", computerChoice);
        } else {
            System.out.printf("There is a draw (%s)%n", userInput);
            user.increaseScore(POINTS_DRAW);
        }
    }

    private File readFile() {
        return new File(SCORE_FILE_NAME);
    }

    private void setScoreFromFile(User user) throws FileNotFoundException {
        try (Scanner sc = new Scanner(readFile())) {
            while (sc.hasNext()) {
                String[] userData = sc.nextLine().split(" ");
                String playerName = userData[0];
                int playerScore = Integer.parseInt(userData[1]);

                if (user.getName().equals(playerName)) {
                    user.setScore(playerScore);
                    return;
                }
            }
        }
    }

    private User createUser() {
        System.out.print("Enter your name: ");
        return new User(scanner.next());
    }

    public void menu() {
        User user = createUser();
        System.out.printf("Hello, %s%n", user.getName());

        try {
            setScoreFromFile(user);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
        }

        loop: while (true) {
            String userInput = getInput();

            switch (userInput) {
                case "!exit":
                    System.out.println("Bye!");
                    break loop;

                case "!rating":
                    System.out.printf("Your rating: %s%n", user.getScore());
                    break;
                default:
                    if (!isInputValid(userInput)) {
                        continue;
                    }
                    checkWinner(userInput, user);
            }
        }
    }
}
