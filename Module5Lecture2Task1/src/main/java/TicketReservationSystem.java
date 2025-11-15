import java.util.Random;

public class TicketReservationSystem {
    private static final int TOTAL_SEATS = 10;
    private static final int TOTAL_CUSTOMERS = 15;

    public static void main(String[] args) {
        Theater theater = new Theater(TOTAL_SEATS);
        Random random = new Random();

        System.out.println("Initial available seats: " + theater.getAvailableSeats());
        System.out.println("Starting ticket reservations...\n");

        // Create and start customer threads
        Customer[] customers = new Customer[TOTAL_CUSTOMERS];
        for (int i = 0; i < TOTAL_CUSTOMERS; i++) {
            int requestedSeats = random.nextInt(4) + 1; // Random seats between 1-4
            customers[i] = new Customer(theater, i + 1, requestedSeats);
            customers[i].start();
        }

        // Wait for all customers to complete
        for (int i = 0; i < TOTAL_CUSTOMERS; i++) {
            try {
                customers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nFinal available seats: " + theater.getAvailableSeats());
    }
}