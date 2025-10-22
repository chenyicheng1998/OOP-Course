import java.util.Scanner;

public class NameGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hard-coded array of first names and last names
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Henry"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};

        int firstNamesLength = firstNames.length;
        int lastNamesLength = lastNames.length;

        System.out.print("How many random names should the program generate? ");
        int numToGenerate = scanner.nextInt();

        System.out.println("\nGenerated names:");

        for (int i = 0; i < numToGenerate; i++) {

            int firstNameIndex = (int) (Math.random() * firstNamesLength);

            int lastNameIndex = (int) (Math.random() * lastNamesLength);

            String randomFirstName = firstNames[firstNameIndex];
            String randomLastName = lastNames[lastNameIndex];
            String fullName = randomFirstName + " " + randomLastName;

            System.out.println((i + 1) + ". " + fullName);
        }

        scanner.close();
    }
}