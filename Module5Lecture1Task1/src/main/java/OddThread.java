public class OddThread extends Thread {
    private final int start;
    private final int end;

    public OddThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            if (i % 2 != 0) {
                System.out.println("Odd Thread: " + i);
                try {
                    Thread.sleep(10); // Small delay to ensure alternating output
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}