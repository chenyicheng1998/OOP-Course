public class Theater {
    private int availableSeats;

    public Theater(int totalSeats) {
        this.availableSeats = totalSeats;
    }

    public synchronized boolean reserveSeats(int customerId, int numberOfSeats) {
        if (numberOfSeats <= availableSeats) {
            // Simulate some processing time
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }

            availableSeats -= numberOfSeats;
            System.out.println("Customer " + customerId + " reserved " + numberOfSeats + " tickets.");
            return true;
        } else {
            System.out.println("Customer " + customerId + " couldn't reserve " + numberOfSeats + " tickets.");
            return false;
        }
    }

    public synchronized int getAvailableSeats() {
        return availableSeats;
    }
}