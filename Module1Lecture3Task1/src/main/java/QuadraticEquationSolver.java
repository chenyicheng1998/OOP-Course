import java.util.Scanner;

public class QuadraticEquationSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the coefficients for ax^2 + bx + c = 0:");

        System.out.print("Enter a: ");
        double a = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter b: ");
        double b = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter c: ");
        double c = Double.parseDouble(scanner.nextLine());

        // b^2 - 4ac
        double discriminant = Math.pow(b, 2) - 4 * a * c;

        if (a == 0) {
            if (b == 0) {
                System.out.println("No real roots (or infinite solutions if c=0).");
            } else {
                double root = -c / b;
                System.out.printf("This is a linear equation. Root: %.2f\n", root);
            }
        } else if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.printf("Two real roots: Root 1 = %.4f, Root 2 = %.4f\n", root1, root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.printf("One real root (double root): %.4f\n", root);
        } else {
            System.out.println("No real roots.");
        }

        scanner.close();
    }
}