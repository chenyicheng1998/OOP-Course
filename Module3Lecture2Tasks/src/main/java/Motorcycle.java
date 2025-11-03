class Motorcycle extends AbstractVehicle {
    public Motorcycle(String color) {
        // type, color, fuelType, efficiencyValue (Task 4)
        super("Motorcycle", color, "Gasoline", "50 MPG");
    }

    @Override
    public String getInfo() {
        return "Type: " + type + "\nFuel: " + fuelType + "\nColor: " + color;
    }
}