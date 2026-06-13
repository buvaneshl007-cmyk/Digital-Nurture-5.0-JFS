import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;
        Scanner scanner = new Scanner(System.in);
        int guess = 0;
        int attempts = 0;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        
        while (guess != secretNumber) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;
            
            if (guess > secretNumber) {
                System.out.println("Too high! Try again.");
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Correct! You guessed it in " + attempts + " attempts.");
            }
        }
        
        scanner.close();
    }
}
