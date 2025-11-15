public class Customer extends Thread {
    private Theater theater;
    private int customerId;
    private int requestedSeats;

    public Customer(Theater theater, int customerId, int requestedSeats) {
        this.theater = theater;
        this.customerId = customerId;
        this.requestedSeats = requestedSeats;
    }

    @Override
    public void run() {
        theater.reserveSeats(customerId, requestedSeats);
    }
}