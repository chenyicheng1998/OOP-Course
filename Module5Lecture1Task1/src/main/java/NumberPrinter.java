public class NumberPrinter {
    public static void main(String[] args) {
        int start = 1;
        int end = 20;

        OddThread oddThread = new OddThread(start, end);
        EvenThread evenThread = new EvenThread(start, end);

        // Start threads
        oddThread.start();
        evenThread.start();

        // Wait for threads to complete
        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Printing complete.");
    }
}