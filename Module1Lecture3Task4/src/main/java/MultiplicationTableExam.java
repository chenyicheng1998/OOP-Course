import java.util.Scanner;

public class MultiplicationTableExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        // Outer do-while loop: Repeat the entire 10-question test as long as the total score is not equal to 10
        do {
            int currentScore = 0;
            System.out.println("\n--- Starting a new 10-question test ---");

            // Inner for loop: Conduct 10 tests
            for (int i = 1; i <= 10; i++) {
                int factor1 = (int) (Math.random() * 10) + 1;
                int factor2 = (int) (Math.random() * 10) + 1;
                int correctResult = factor1 * factor2;

                System.out.printf("Question %d: What is %d * %d? ", i, factor1, factor2);

                int userAnswer;
                try {
                    userAnswer = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    userAnswer = Integer.MIN_VALUE;
                    System.out.println("Invalid input! Skipping question.");
                }

                if (userAnswer == correctResult) {
                    currentScore++;
                    System.out.println("Correct!");
                } else {
                    System.out.printf("Incorrect. The answer is %d.\n", correctResult);
                }
            }

            totalScore = currentScore;
            System.out.printf("\n--- Test finished! Your score: %d/10 ---\n", totalScore);

            // Check if it has reached the full score
            if (totalScore == 10) {
                System.out.println("CONGRATULATIONS! You have mastered the multiplication tables!");
            } else {
                System.out.println("Keep practicing! Starting a new set of problems...");
            }

        } while (totalScore != 10); // When the score is not 10, repeat the do-while loop

        scanner.close();
    }
}