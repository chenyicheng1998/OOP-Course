public class Bus extends Car {
    private int passengerCount;
    private int maxPassengers;

    public Bus(String typeName) {
        super(typeName, 200, 120); // Large tank, lower max speed
        this.passengerCount = 0;
        this.maxPassengers = 50;
    }

    public Bus(String typeName, double tankCapacity, double maxSpeed, int maxPassengers) {
        super(typeName, tankCapacity, maxSpeed);
        this.passengerCount = 0;
        this.maxPassengers = maxPassengers;
    }

    public void passengerEnter(int number) {
        if (number > 0 && passengerCount + number <= maxPassengers) {
            passengerCount += number;
            System.out.println(number + " passengers entered. Total: " + passengerCount);
        } else {
            System.out.println("Cannot accommodate " + number + " passengers. Current: " +
                    passengerCount + ", Max: " + maxPassengers);
        }
    }

    public void passengerExit(int number) {
        if (number > 0 && passengerCount >= number) {
            passengerCount -= number;
            System.out.println(number + " passengers exited. Total: " + passengerCount);
        } else {
            System.out.println("Cannot exit " + number + " passengers. Current: " + passengerCount);
        }
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }
    
    @Override
    public String toString() {
        return String.format("Bus - %s, passengers=%d/%d",
                super.toString(), passengerCount, maxPassengers);
    }
}