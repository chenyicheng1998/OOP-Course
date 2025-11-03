abstract class AbstractVehicle implements Vehicle, ElectricVehicle {
    protected String type;
    protected String color;
    protected String fuelType;
    protected String efficiencyValue; // Stores the fuel/energy efficiency value for Task 4

    public AbstractVehicle(String type, String color, String fuelType, String efficiencyValue) {
        this.type = type;
        this.color = color;
        this.fuelType = fuelType;
        this.efficiencyValue = efficiencyValue;
    }

    @Override
    public void start() {
        System.out.println(type + " is starting...");
    }

    @Override
    public void stop() {
        System.out.println(type + " is stopping...");
    }

    // Default implementation for non-electric vehicles as per Task 3's suggestion.
    @Override
    public void charge() {
        System.out.println(type + ": Not possible to charge. This is not an electric vehicle.");
    }

    @Override
    public String calculateFuelEfficiency() {
        // Implementation based on the stored efficiencyValue (Task 4)
        return this.efficiencyValue;
    }
}