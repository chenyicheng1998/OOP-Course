public class CarWithCruiseControl {
    private double speed;
    private double gasolineLevel;
    private String typeName;
    private double tankCapacity;
    private double maxSpeed;

    // Attributes related to cruise control
    private boolean cruiseControlOn;
    private double targetSpeed;
    private final double MIN_CRUISE_SPEED = 30.0;
    private final double MAX_CRUISE_SPEED = 120.0;

    public CarWithCruiseControl(String typeName, double tankCapacity, double maxSpeed) {
        this.speed = 0;
        this.gasolineLevel = 0;
        this.typeName = typeName;
        this.tankCapacity = tankCapacity;
        this.maxSpeed = maxSpeed;
        this.cruiseControlOn = false;
        this.targetSpeed = 0;
    }

    public void accelerate() {
        if (gasolineLevel > 0) {
            if (cruiseControlOn) {
                // In cruise control mode, try to reach the target speed
                if (speed < targetSpeed) {
                    speed = Math.min(speed + 10, targetSpeed);
                }
            } else {
                speed = Math.min(speed + 10, maxSpeed);
            }
            gasolineLevel = Math.max(0, gasolineLevel - 0.5);
        } else {
            speed = 0;
            cruiseControlOn = false; // Turn off the cruise control when there is no fuel
        }
    }

    public void decelerate(int amount) {
        if (gasolineLevel > 0) {
            if (amount > 0) {
                if (cruiseControlOn) {
                    // In cruise control mode, try to reach the target speed
                    if (speed > targetSpeed) {
                        speed = Math.max(speed - amount, targetSpeed);
                    }
                } else {
                    speed = Math.max(0, speed - amount);
                }
                gasolineLevel = Math.max(0, gasolineLevel - 0.2);
            }
        } else {
            speed = 0;
            cruiseControlOn = false; // Turn off the cruise control when there is no fuel
        }
    }

    // Cruise control method
    public boolean setTargetSpeed(double speed) {
        if (speed >= MIN_CRUISE_SPEED && speed <= MAX_CRUISE_SPEED) {
            this.targetSpeed = speed;
            return true;
        }
        return false;
    }

    public double getTargetSpeed() {
        return targetSpeed;
    }

    public boolean turnOnCruiseControl() {
        if (targetSpeed >= MIN_CRUISE_SPEED && targetSpeed <= MAX_CRUISE_SPEED && gasolineLevel > 0) {
            cruiseControlOn = true;
            return true;
        }
        cruiseControlOn = false;
        return false;
    }

    public void turnOffCruiseControl() {
        cruiseControlOn = false;
    }

    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    public double getMinCruiseSpeed() {
        return MIN_CRUISE_SPEED;
    }

    public double getMaxCruiseSpeed() {
        return MAX_CRUISE_SPEED;
    }

    // Other getter methods
    public double getSpeed() {
        return speed;
    }

    public String getTypeName() {
        return typeName;
    }

    public void fillTank() {
        gasolineLevel = tankCapacity;
    }

    public double getGasolineLevel() {
        return gasolineLevel;
    }
}