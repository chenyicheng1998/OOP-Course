class ElectricCar extends AbstractVehicle {
    public ElectricCar(String color) {
        // type, color, fuelType (Electric), efficiencyValue (Task 4)
        super("Electric Car", color, "Electricity", "5 km/kWh");
    }

    @Override
    public void charge() {
        System.out.println(type + " is plugging in and charging its battery.");
    }

    @Override
    public String getInfo() {
        return "Type: " + type + "\nFuel: " + fuelType + "\nColor: " + color;
    }
}