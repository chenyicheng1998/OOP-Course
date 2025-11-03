class Car extends AbstractVehicle {
    public Car(String color) {
        // type, color, fuelType, efficiencyValue (Task 4)
        super("Car", color, "Petrol", "30 MPG");
    }

    @Override
    public String getInfo() {
        return "Type: " + type + "\nFuel: " + fuelType + "\nColor: " + color;
    }
}