class ElectricMotorcycle extends AbstractVehicle {
    public ElectricMotorcycle(String color) {
        // type, color, fuelType (Electric), efficiencyValue (Task 4)
        super("Electric Motorcycle", color, "Electricity", "10 km/kWh");
    }

    @Override
    public void charge() {
        System.out.println(type + " is connecting to a charging station.");
    }

    @Override
    public String getInfo() {
        return "Type: " + type + "\nFuel: " + fuelType + "\nColor: " + color;
    }
}