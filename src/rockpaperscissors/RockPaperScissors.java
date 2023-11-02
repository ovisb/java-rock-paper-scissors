package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

class RockPaperScissors {
    private final Scanner scanner = new Scanner(System.in);

    private final String[] COMP_CHOICES = {"rock", "paper", "scissors"};

    private final Random random = new Random();
    private String getInput() {
        return scanner.next();
    }

    private boolean isInputValid(String userInput) {
        boolean isValid = false;
        for (String choice: COMP_CHOICES) {
            if (choice.equals(userInput)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    private String findWhoBeatsChoice(String choice) {
        String winner;
        if (choice.equals("rock")) {
            winner = "paper";
        } else if (choice.equals("paper")) {
            winner = "scissors";
        } else {
            winner = "rock";
        }
        return winner;
    }

    private void checkWinner(String userInput) {
        int randomInt = random.nextInt(COMP_CHOICES.length);

        String computerChoice = COMP_CHOICES[randomInt];
        String whoBeatsUser = findWhoBeatsChoice(userInput);
        String whoBeatsComp = findWhoBeatsChoice(computerChoice);

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
