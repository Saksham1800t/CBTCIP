import java.util.Random;
import java.util.Scanner;

public class Task_1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();

            int minRange = 1;
            int maxRange = 100;
            int attempts = 0;
            int score = 0;

            System.out.println("Welcome to Guess the Number!");
            System.out.println("I've selected a random number between " + minRange + " and " + maxRange + ".");
            System.out.println("Try to guess it within a limited number of attempts.");

            boolean playAgain = true;

            while (playAgain) {
                int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
                int maxAttempts = 7; // You can change this to set a different limit on attempts.

                System.out.println("\nNew round! You have " + maxAttempts + " attempts.");
                attempts = 0;

                while (attempts < maxAttempts) {
                    System.out.print("Enter your guess: ");
                    int userGuess = scanner.nextInt();
                    attempts++;

                    if (userGuess < minRange || userGuess > maxRange) {
                        System.out.println("Please guess a number between " + minRange + " and " + maxRange + ".");
                    } else if (userGuess < targetNumber) {
                        System.out.println("Try higher.");
                    } else if (userGuess > targetNumber) {
                        System.out.println("Try lower.");
                    } else {
                        System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts.");
                        int points = maxAttempts - attempts + 1; // Calculate score based on attempts.
                        System.out.println("You've earned " + points + " points.");
                        score += points;
                        break;
                    }

                    if (attempts == maxAttempts) {
                        System.out.println("Sorry, you've run out of attempts. The number was " + targetNumber + ".");
                    }
                }

                System.out.print("Your total score is: " + score);
                System.out.println("\nDo you want to play again? (yes/no): ");
                String playAgainResponse = scanner.next().toLowerCase();
                playAgain = playAgainResponse.equals("yes");
            }
        }
        System.out.println("Thanks for playing Guess the Number!");
    }
}
