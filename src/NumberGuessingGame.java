import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("      NUMBER GUESSING GAME");
        System.out.println("=================================");

        boolean playAgain = true;
        int totalGames = 0;
        int totalAttempts = 0;
        int wonGames = 0;

        while (playAgain) {

            // Generate random number from 1 to 100
            int number = random.nextInt(100) + 1;

            int attempts = 0;
            int maxAttempts = 7;
            boolean won = false;

            System.out.println();
            System.out.println("I have selected a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it!");

            // Guessing loop
            while (attempts < maxAttempts) {

                System.out.print(
                        "Enter your guess (" +
                                (attempts + 1) +
                                "/" +
                                maxAttempts +
                                "): "
                );

                int guess;

                // Input validation
                try {
                    guess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid input! Please enter a number."
                    );

                    continue;
                }

                // Count the attempt
                attempts++;

                // Compare guess with random number
                if (guess > number) {

                    System.out.println("Too High!");

                } else if (guess < number) {

                    System.out.println("Too Low!");

                } else {

                    System.out.println(
                            "Correct! You guessed the number!"
                    );

                    System.out.println(
                            "You got it in " +
                                    attempts +
                                    " attempts."
                    );

                    won = true;

                    totalAttempts += attempts;
                    wonGames++;

                    break;
                }

                // Hint after half of the attempts
                if (attempts == 3) {

                    if (number % 2 == 0) {

                        System.out.println(
                                "Hint: The number is EVEN."
                        );

                    } else {

                        System.out.println(
                                "Hint: The number is ODD."
                        );
                    }
                }
            }

            // Player loses
            if (!won) {

                System.out.println();
                System.out.println("You Lost!");

                System.out.println(
                        "You used all " +
                                maxAttempts +
                                " attempts."
                );

                System.out.println(
                        "The correct number was: " +
                                number
                );
            }

            // Increase round number
            totalGames++;

            // Round summary
            System.out.println();
            System.out.println("---------------------------------");

            if (won) {

                System.out.println(
                        "Round " +
                                totalGames +
                                " — guessed in " +
                                attempts +
                                " attempts."
                );

            } else {

                System.out.println(
                        "Round " +
                                totalGames +
                                " — You Lost!"
                );
            }

            System.out.println("---------------------------------");

            // Play again
            while (true) {

                System.out.print(
                        "Play again? (yes/no): "
                );

                String response =
                        scanner.nextLine().toLowerCase();

                if (response.equals("yes") ||
                        response.equals("y")) {

                    playAgain = true;
                    break;

                } else if (response.equals("no") ||
                        response.equals("n")) {

                    playAgain = false;
                    break;

                } else {

                    System.out.println(
                            "Please enter yes or no."
                    );
                }
            }
        }

        // Final statistics
        System.out.println();
        System.out.println("=================================");
        System.out.println("        GAME STATISTICS");
        System.out.println("=================================");

        System.out.println(
                "Total rounds played: " +
                        totalGames
        );

        System.out.println(
                "Rounds won: " +
                        wonGames
        );

        System.out.println(
                "Rounds lost: " +
                        (totalGames - wonGames)
        );

        if (wonGames > 0) {

            double averageAttempts =
                    (double) totalAttempts / wonGames;

            System.out.printf(
                    "Average attempts in won rounds: %.2f%n",
                    averageAttempts
            );
        }

        System.out.println();
        System.out.println("Thanks for playing!");
        System.out.println("=================================");

        scanner.close();
    }
}