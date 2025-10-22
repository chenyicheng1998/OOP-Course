import java.util.Scanner;

public class RemoveDuplicates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        int[] originalArray = new int[size];
        System.out.println("Enter the integers into the array:");

        for (int i = 0; i < size; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            originalArray[i] = scanner.nextInt();
            scanner.nextLine();
        }

        int[] uniqueArray = new int[size];
        int uniqueCount = 0;

        for (int i = 0; i < size; i++) {
            int currentNumber = originalArray[i];
            boolean isDuplicate = false;

            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueArray[j] == currentNumber) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                uniqueArray[uniqueCount] = currentNumber;
                uniqueCount++;
            }
        }

        System.out.println("\nThe array without duplicates:");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.print(uniqueArray[i] + (i < uniqueCount - 1 ? " " : ""));
        }
        System.out.println();

        scanner.close();
    }
}