public class VehicleDemo {

    /**
     * A utility method to interact with any Vehicle object (polymorphism).
     */
    private static void demonstrateVehicle(Vehicle vehicle) {
        String type = "";
        try {
            // Use reflection to get the actual class name for a nicer output format.
            type = vehicle.getClass().getSimpleName();
        } catch (Exception e) {
            // Fallback for types without a simple name (like anonymous classes)
            type = "Vehicle";
        }

        System.out.println("\n--- " + type + " ---");
        vehicle.start();
        vehicle.stop();

        // Task 3: Check if it's an ElectricVehicle to call charge()
        if (vehicle instanceof ElectricVehicle) {
            ((ElectricVehicle) vehicle).charge();
        }

        System.out.println("\n" + type + " Information:");
        System.out.println(vehicle.getInfo());

        // Task 4: Fuel Efficiency Calculation
        System.out.println("Fuel Efficiency: " + vehicle.calculateFuelEfficiency());
    }

    public static void main(String[] args) {
        System.out.println("Vehicle Demonstration\n");

        // Create instances of all vehicles
        Vehicle car = new Car("Red");
        Vehicle motorcycle = new Motorcycle("Black");
        Vehicle bus = new Bus("Yellow", 40);
        Vehicle electricCar = new ElectricCar("Silver");
        Vehicle electricMotorcycle = new ElectricMotorcycle("Blue");

        // Demonstrate all vehicles using the polymorphic method
        demonstrateVehicle(car);
        demonstrateVehicle(motorcycle);
        demonstrateVehicle(bus);
        demonstrateVehicle(electricCar);
        demonstrateVehicle(electricMotorcycle);
    }
}