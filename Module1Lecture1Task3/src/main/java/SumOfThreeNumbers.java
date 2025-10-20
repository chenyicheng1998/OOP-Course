import java.util.Scanner;

// The expression uses 3.0 instead of 3 to perform floating-point division,
// ensuring the result of the average is a double (which can include a decimal
// fraction) rather than an integer (which would truncate the decimal part).


public class SumOfThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Give the first number:");
        int first = Integer.parseInt(scanner.nextLine());

        System.out.println("Give the second number:");
        int second = Integer.parseInt(scanner.nextLine());

        System.out.println("Give the third number:");
        int third = Integer.parseInt(scanner.nextLine());

        System.out.println("The sum of the numbers is " + (first + second + third));
        System.out.println("The manipulation of the numbers is " + (first * second * third));
        System.out.println("The average of the numbers is " + (first + second + third)/3.0);
    }
}