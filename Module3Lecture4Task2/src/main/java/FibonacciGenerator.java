import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FibonacciGenerator {

    /**
     * Generates the first n terms of the Fibonacci sequence.
     * The sequence starts with F(0)=0 and F(1)=1.
     *
     * @param n The number of terms to generate.
     * @return A List of Longs containing the Fibonacci sequence.
     */
    public static List<Long> generateFibonacci(int n) {
        List<Long> fibonacci = new ArrayList<>();

        if (n >= 1) {
            // F(0)
            fibonacci.add(0L);
        }
        if (n >= 2) {
            // F(1)
            fibonacci.add(1L);
        }

        // Calculate subsequent terms
        for (int i = 2; i < n; i++) {
            long next = fibonacci.get(i - 1) + fibonacci.get(i - 2);
            fibonacci.add(next);
        }

        return fibonacci;
    }

    /**
     * Saves the generated Fibonacci sequence to a CSV file.
     *
     * @param fibonacci The List of Fibonacci numbers.
     * @param filename  The name of the CSV file to save to.
     */
    public static void saveToCSV(List<Long> fibonacci, String filename) {
        String currentDir = new File("").getAbsolutePath();
        String filePath = currentDir + File.separator + filename;

        try (FileWriter writer = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            bufferedWriter.write("Index,Fibonacci Number");
            bufferedWriter.newLine();

            for (int i = 0; i < fibonacci.size(); i++) {
                String line = (i + 1) + "," + fibonacci.get(i);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            System.out.println("Fibonacci sequence saved to file: " + filePath);

        } catch (IOException e) {
            System.err.println("Error while saving the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int numberOfTerms = 60;
        List<Long> fibonacci = generateFibonacci(numberOfTerms);

        // Print the first few numbers for verification
        System.out.println("First 10 Fibonacci numbers:");
        for (int i = 0; i < 10 && i < fibonacci.size(); i++) {
            System.out.println((i + 1) + ": " + fibonacci.get(i));
        }
        System.out.println("...");
        // F(59) since the list is 0-indexed
        System.out.println("The 60th Fibonacci number (F(59)): " + fibonacci.get(59));

        // Save to CSV file
        saveToCSV(fibonacci, "fibonacci_sequence.csv");

        // Verify file content by reading the first few lines
        System.out.println("\nVerifying file content...");
        try (FileReader reader = new FileReader("fibonacci_sequence.csv");
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;
            int lineCount = 0;
            // Read and print header + first 4 data rows
            while ((line = bufferedReader.readLine()) != null && lineCount < 5) {
                System.out.println(line);
                lineCount++;
            }
            if (lineCount == 5) {
                System.out.println("... (Open the file with spreadsheet software for more content)");
            }

        } catch (IOException e) {
            System.err.println("Error while reading the verification file: " + e.getMessage());
        }
    }
}