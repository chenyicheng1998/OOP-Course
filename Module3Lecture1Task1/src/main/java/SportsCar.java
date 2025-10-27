public class SportsCar extends Car {
    public SportsCar(String typeName) {
        super(typeName, 80, 300); // Smaller tank but higher max speed
    }

    public SportsCar(String typeName, double tankCapacity, double maxSpeed) {
        super(typeName, tankCapacity, maxSpeed);
    }

    @Override
    public void accelerate() {
        if (getGasolineLevel() > 0) {
            setSpeed(Math.min(getSpeed() + 25, getMaxSpeed())); // Faster acceleration
            setGasolineLevel(Math.max(0, getGasolineLevel() - 1.5)); // Higher consumption
        } else {
            setSpeed(0);
        }
    }

    @Override
    public void decelerate(int amount) {
        if (getGasolineLevel() > 0) {
            if (amount > 0) {
                setSpeed(Math.max(0, getSpeed() - amount * 2)); // Faster deceleration
                setGasolineLevel(Math.max(0, getGasolineLevel() - 0.3)); // Higher consumption
            }
        } else {
            setSpeed(0);
        }
    }

    @Override
    public String toString() {
        return "SportsCar - " + super.toString();
    }
}