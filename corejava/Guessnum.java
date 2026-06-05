import java.util.Random;
import java.util.Scanner;

public class Guessnum {

    public static void main(String[] args) {

        Random random = new Random();

        Scanner sc = new Scanner(System.in);

        int secretNumber = random.nextInt(100) + 1;

        int guess = 0;

        System.out.println("Guess a number between 1 and 100");

        while (guess != secretNumber) {

            System.out.println("Enter your guess:");

            guess = sc.nextInt();

            if (guess > secretNumber) {

                System.out.println("Too High!");

            } else if (guess < secretNumber) {

                System.out.println("Too Low!");

            } else {

                System.out.println("Correct! You guessed the number.");
            }
        }

        sc.close();
    }
}