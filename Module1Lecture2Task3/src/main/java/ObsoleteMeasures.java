import java.util.Scanner;

public class ObsoleteMeasures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final double GRAMS_PER_LUOTI = 13.28;
        final int LUOTI_PER_NAULA = 32;
        final int NAULA_PER_LEIVISKA = 20;

        // 计算一个 leiviskä 等于多少 luoti
        final int LUOTI_PER_LEIVISKA = NAULA_PER_LEIVISKA * LUOTI_PER_NAULA; // 20 * 32 = 640

        System.out.print("Weight (g): ");
        double totalGrams = Double.parseDouble(scanner.nextLine());

        double totalLuoti = totalGrams / GRAMS_PER_LUOTI;

        int leiviska = (int) (totalLuoti / LUOTI_PER_LEIVISKA);

        double remainingLuotiAfterLeiviska = totalLuoti % LUOTI_PER_LEIVISKA;

        int naula = (int) (remainingLuotiAfterLeiviska / LUOTI_PER_NAULA);

        double remainingLuoti = remainingLuotiAfterLeiviska % LUOTI_PER_NAULA;

        System.out.printf("%.0f grams is %d leiviskä, %d naula, and %.2f luoti.\n",
                totalGrams, leiviska, naula, remainingLuoti);

        scanner.close();
    }
}