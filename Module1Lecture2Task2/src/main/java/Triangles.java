import java.util.Scanner;

public class Triangles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the length of the first leg (a): ");
        double leg1 = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the length of the second leg (b): ");
        double leg2 = Double.parseDouble(scanner.nextLine());

        // c = sqrt(a^2 + b^2)
        double hypotenuse = Math.sqrt(Math.pow(leg1, 2) + Math.pow(leg2, 2));

        System.out.println("The length of the hypotenuse (c) is: " + hypotenuse);

        scanner.close();
    }
}