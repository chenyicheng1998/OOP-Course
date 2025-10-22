import java.util.Scanner;

public class PrimeNumberGenerator {

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start, end;

        while (true) {
            System.out.print("Enter the start integer ( > 0): ");
            start = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter the end integer ( > start): ");
            end = Integer.parseInt(scanner.nextLine());

            if (start > 0 && start < end) {
                break;
            } else {
                System.out.println("Invalid input! Please ensure start > 0 and start < end.");
            }
        }

        System.out.printf("\nPrime numbers between %d and %d (inclusive):\n", start, end);

        int count = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No prime numbers found in this range.");
        } else {
            System.out.println("\nTotal prime numbers found: " + count);
        }

        scanner.close();
    }
}