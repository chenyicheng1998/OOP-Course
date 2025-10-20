import java.util.Scanner;

public class FahrenheitToCelsius {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a temperature in Fahrenheit: ");

        double fahrenheit = Double.parseDouble(scanner.nextLine());

        // C = (F - 32) / 1.8
        double celsius = (fahrenheit - 32) / 1.8;

        System.out.printf("The temperature in Celsius is: %.1f\n", celsius);

        scanner.close();
    }
}