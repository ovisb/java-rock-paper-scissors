package rockpaperscissors;

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

    /**
     * Validate user input
     * @param allChoices Initial list containing all the games choices
     * @param userInput User input
     * @return true if valid else false
     */
    private boolean isInputValid(String[] allChoices, String userInput) {
        for (String choice: allChoices) {
            if (userInput.equals(choice)) {
                return true;
            }
        }
        System.out.println("Invalid input");
        return false;
    }

    /**
     * Return the options which beat the User's option
     * First half of the array are the options who beat the user, while second half are the one he loses to
     * @param finalChoices Final list containing all winner / losing options
     * @return first half of winner options
     */
    private String[] getWinners(String[] finalChoices) {
        int mid = finalChoices.length / 2;
        String[] winners = new String[mid];

        System.arraycopy(finalChoices, 0, winners, 0, mid);
        return winners;
    }

    /**
     * Get computer choice
     * @param allChoices Initial list containing all the games choices
     * @return Computer choice
     */
    private String getComputerChoice(String[] allChoices) {
        int randomInt = random.nextInt(allChoices.length);
        return allChoices[randomInt];
    }

    /**
     * Find who won or if it's a draw
     * @param allChoices Initial list containing all the games choices
     * @param finalChoices Final list containing all winner / losing options
     * @param userInput User input
     * @param user User object
     */
    private void checkWinner(String[] allChoices, String[] finalChoices, String userInput, User user) {
        String computerChoice = getComputerChoice(allChoices);

        String[] winsAgainstUser = getWinners(finalChoices);

        if (userInput.equals(computerChoice)) {
            System.out.printf("There is a draw (%s)%n", userInput);
            user.increaseScore(POINTS_DRAW);
            return;
        }

        for (String s: winsAgainstUser) {
            if (s.equals(computerChoice)) {
                System.out.printf("Sorry, but the computer chose %s%n", computerChoice);
                return;
            }
        }

        System.out.printf("Well done. The computer chose %s and failed%n", computerChoice);
        user.increaseScore(POINTS_WIN);
    }

    /**
     * Create user
     * @return user object
     */
    private User createUser() {
        System.out.print("Enter your name: ");
        return new User(scanner.next());
    }

    /**
     * Return index location of the user's choice
     * @param allChoices Initial list containing all the games choices
     * @param choice User choice
     * @return index
     */
    private int getChoiceIndex(String[] allChoices, String choice) {
        for (int i = 0; i < allChoices.length; i++) {
            if (choice.equals(allChoices[i])) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Get all the options excluding the user choice
     * Return a new list counting with the item that follows the user choice till the very end of the list
     * Then also add the remaining items starting from the beginning of the list
     * @param allChoices Initial list containing all the games choices
     * @param userChoice User choice
     * @return Array of the options described above
     */
    private String[] optionsWithoutChoice(String[] allChoices, String userChoice) {
        // get location
        int choiceIdx = getChoiceIndex(allChoices, userChoice);
        String[] option = new String[allChoices.length - 1];

        // get all options that follows the user choice
        int i = 0;
        while (choiceIdx != allChoices.length - 1) {
            option[i++] = allChoices[++choiceIdx];
        }

        // get remaining choices starting from the beginning
        int j = 0;
        while (!allChoices[j].equals(userChoice)) {
            option[i++] = allChoices[j++];
        }

        return option;
    }

    /**
     * Main game menu
     */
    public void menu() {
        User user = createUser();
        System.out.printf("Hello, %s%n", user.getName());

        Scanner sc = new Scanner(System.in);
        String gameChoices = sc.nextLine();

        if (gameChoices.isEmpty()) {
            gameChoices = "rock,paper,scissors";
        }
        System.out.println("Okay, let's start");
        String[] allChoices = gameChoices.split(",");
        String[] finalChoices;

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
                    if (!isInputValid(allChoices, userInput)) {
                        continue;
                    }
                    finalChoices = optionsWithoutChoice(allChoices, userInput);
                    checkWinner(allChoices, finalChoices, userInput, user);
            }
        }
    }
}
