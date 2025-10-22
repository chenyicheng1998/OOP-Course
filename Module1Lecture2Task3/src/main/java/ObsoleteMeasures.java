import java.util.Scanner;

public class ObsoleteMeasures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final double GRAMS_PER_LUOTI = 13.28;
        final int LUOTI_PER_NAULA = 32;
        final int NAULA_PER_LEIVISKÄ = 20;

        final int LUOTI_PER_LEIVISKÄ = NAULA_PER_LEIVISKÄ * LUOTI_PER_NAULA;

        System.out.print("Weight (g): ");
        double totalGrams = Double.parseDouble(scanner.nextLine());

        double totalLuoti = totalGrams / GRAMS_PER_LUOTI;

        int leiviska = (int) (totalLuoti / LUOTI_PER_LEIVISKÄ);

        double remainingLuotiAfterLeiviskä = totalLuoti % LUOTI_PER_LEIVISKÄ;

        int naula = (int) (remainingLuotiAfterLeiviskä / LUOTI_PER_NAULA);

        double remainingLuoti = remainingLuotiAfterLeiviskä % LUOTI_PER_NAULA;

        System.out.printf("%.0f grams is %d leiviskä, %d naula, and %.2f luoti.\n",
                totalGrams, leiviska, naula, remainingLuoti);

        scanner.close();
    }
}