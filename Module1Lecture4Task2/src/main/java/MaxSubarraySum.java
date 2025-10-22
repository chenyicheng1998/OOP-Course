import java.util.Scanner;

public class MaxSubarraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        int[] numbers = new int[size];
        System.out.println("Enter the integers into the array:");

        for (int i = 0; i < size; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
            scanner.nextLine();
        }


        long maxSum = Long.MIN_VALUE;
        int startMaxIndex = 0;
        int endMaxIndex = 0;

        for (int i = 0; i < size; i++) {
            long currentSum = 0;

            for (int j = i; j < size; j++) {
                currentSum += numbers[j];

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    startMaxIndex = i;
                    endMaxIndex = j;
                }
            }
        }

        System.out.println("\nMaximum sum: " + maxSum);
        System.out.println("Integers: " + (startMaxIndex + 1) + "-" + (endMaxIndex + 1));

        scanner.close();
    }
}