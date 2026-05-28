import java.util.Scanner;

public class calculator {

    public static void main(String[] args) {

        Scanner num = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        int num1 = num.nextInt();

        System.out.println("Enter the second number: ");
        int num2 = num.nextInt();

        System.out.println("Enter the operator (+, -, *, /): ");
        char operator = num.next().charAt(0);

        int result;

        switch (operator) {

            case '+':
                result = num1 + num2;
                System.out.println("Result: " + result);
                break;

            case '-':
                result = num1 - num2;
                System.out.println("Result: " + result);
                break;

            case '*':
                result = num1 * num2;
                System.out.println("Result: " + result);
                break;

            case '/':

                if (num2 != 0) {
                    result = num1 / num2;
                    System.out.println("Result: " + result);
                } else {
                    System.out.println("Error: Division by zero");
                }

                break;

            default:
                System.out.println("Invalid operator");
        }

        num.close();
    }
}