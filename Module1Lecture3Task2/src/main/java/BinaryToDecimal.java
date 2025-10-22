import java.util.Scanner;

public class BinaryToDecimal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a binary number (0s and 1s): ");
        String binaryString = scanner.nextLine();

        // Make sure the input is a valid binary string
        if (!binaryString.matches("[01]+")) {
            System.out.println("Error: Invalid binary format.");
            scanner.close();
            return;
        }

        long decimalValue = 0;
        int powerOfTwo = 1;

        // Start traversing from the right side of the string (the least significant bit)
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            char bitChar = binaryString.charAt(i);

            int bit = Character.getNumericValue(bitChar);

            if (bit == 1) {
                decimalValue += powerOfTwo;
            }

            powerOfTwo *= 2;
        }

        System.out.println("The decimal equivalent is: " + decimalValue);

        scanner.close();
    }
}