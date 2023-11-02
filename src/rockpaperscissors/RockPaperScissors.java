package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

class RockPaperScissors {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private String getInput() {
        return scanner.next();
    }

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
        }

        return isValid;
    }

    private String findWhoBeatsChoice(GameChoices choice) {
        return choice.getLosesAgainst();
    }

    private void checkWinner(String userInput) {
        GameChoices[] availableChoices = GameChoices.values();
        int randomInt = random.nextInt(availableChoices.length);
        String computerChoice = availableChoices[randomInt].name().toLowerCase();

        GameChoices userChoice = GameChoices.valueOf(userInput.toUpperCase());
        GameChoices compChoice = GameChoices.valueOf(computerChoice.toUpperCase());

        String whoBeatsUser = findWhoBeatsChoice(userChoice);
        String whoBeatsComp = findWhoBeatsChoice(compChoice);

        if (userInput.equals(whoBeatsComp)) {
            System.out.printf("Well done. The computer chose %s and failed%n", computerChoice);
        } else if (computerChoice.equals(whoBeatsUser)) {
            System.out.printf("Sorry, but the computer chose %s%n", computerChoice);
        } else {
            System.out.printf("There is a draw %s%n", userInput);
        }
    }

    public void menu() {
        while (true) {
            String userInput = getInput();

            if (userInput.equals("!exit")) {
                System.out.println("Bye!");
                break;
            }

            if (!isInputValid(userInput)) {
                System.out.println("Invalid input");
                continue;
            }

            checkWinner(userInput);
        }
    }
}
