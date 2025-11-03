class Bus extends AbstractVehicle {
    private int capacity;

    public Bus(String color, int capacity) {
        // type, color, fuelType, efficiencyValue (Task 4)
        super("Bus", color, "Diesel", "8 MPG");
        this.capacity = capacity;
    }

    @Override
    public String getInfo() {
        return "Type: " + type + "\nFuel: " + fuelType + "\nColor: " + color + "\nCapacity: " + capacity + " passengers";
    }
}