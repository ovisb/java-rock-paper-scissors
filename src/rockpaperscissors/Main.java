package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String[] COMP_CHOICES = {"rock", "paper", "scissors"};

    private static final Random random = new Random();
    public static String getInput() {
        return scanner.next();
    }

    public static void checkWinner(String userInput) {
        int randomInt = random.nextInt(COMP_CHOICES.length);
        String computer;

        if (userInput.equals("rock")) {
            computer = "paper";
        }
        else if (userInput.equals("paper")) {
            computer = "scissors";
        }
        else {
            computer = "rock";
        }

        System.out.printf("Sorry, but the computer chose %s%n", computer);
    }

    public static void main(String[] args) {
        // write your code here
        String userInput = getInput();
        checkWinner(userInput);
    }
}
