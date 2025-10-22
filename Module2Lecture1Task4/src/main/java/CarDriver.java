public class CarDriver {
    public static void main(String[] args) {
        CarWithCruiseControl myCar = new CarWithCruiseControl("Toyota Corolla", 60, 180);

        myCar.fillTank();

        // Test the cruise control
        System.out.println("=== Testing Cruise Control ===");

        // Set the target speed
        if (myCar.setTargetSpeed(80)) {
            System.out.println("Target speed set to: " + myCar.getTargetSpeed() + " km/h");
        }

        // Attempt to activate cruise control (it should fail because the vehicle speed is 0)
        if (myCar.turnOnCruiseControl()) {
            System.out.println("Cruise control turned ON");
        } else {
            System.out.println("Cannot turn on cruise control - speed too low");
        }

        // Accelerate to a speed at which cruise control can be activated
        System.out.println("\nAccelerating to minimum cruise speed...");
        while (myCar.getSpeed() < myCar.getMinCruiseSpeed()) {
            myCar.accelerate();
            System.out.println("Current speed: " + myCar.getSpeed() + " km/h");
        }

        // Now activate the cruise control
        if (myCar.turnOnCruiseControl()) {
            System.out.println("Cruise control turned ON at " + myCar.getSpeed() + " km/h");
        }

        // Keep driving for a while
        System.out.println("\nDriving with cruise control...");
        for (int i = 0; i < 5; i++) {
            myCar.accelerate();
            System.out.println("Speed: " + myCar.getSpeed() + " km/h, Target: " + myCar.getTargetSpeed() + " km/h");
        }

        // Turn off cruise control
        System.out.println("\nTurning off cruise control...");
        myCar.turnOffCruiseControl();

        // decelerate
        System.out.println("\nDecelerating...");
        while (myCar.getSpeed() > 0) {
            myCar.decelerate(20);
            System.out.println("Current speed: " + myCar.getSpeed() + " km/h");
        }

        System.out.println("Gasoline level: " + myCar.getGasolineLevel() + " liters");
    }
}