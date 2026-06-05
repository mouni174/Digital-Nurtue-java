
import java.util.Scanner;

public class Gradecalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the marks out of 100:");
        int grade = sc.nextInt();
        if(grade >= 90 && grade <= 100) {
            System.out.println("Grade: A");
        } else if(grade >= 80 && grade < 90) {
            System.out.println("Grade: B");
        } else if(grade >= 70 && grade < 80) {
            System.out.println("Grade: C");
        } else if(grade >= 60 && grade < 70) {
            System.out.println("Grade: D");
        } else if(grade >= 0 && grade < 60) {
            System.out.println("Grade: F");
        } else {
            System.out.println("Invalid marks entered.");
        }
    }
}
