public class Car {
    private double speed;
    private double gasolineLevel;
    private String typeName;
    private double tankCapacity;
    private double maxSpeed;

    public Car(String typeName) {
        this.speed = 0;
        this.gasolineLevel = 0;
        this.typeName = typeName;
        this.tankCapacity = 100;
        this.maxSpeed = 200;
    }

    public Car(String typeName, double tankCapacity, double maxSpeed) {
        this.speed = 0;
        this.gasolineLevel = 0;
        this.typeName = typeName;
        this.tankCapacity = tankCapacity;
        this.maxSpeed = maxSpeed;
    }

    public void accelerate() {
        if (gasolineLevel > 0) {
            speed = Math.min(speed + 10, maxSpeed);
            gasolineLevel = Math.max(0, gasolineLevel - 0.5);
        } else {
            speed = 0;
        }
    }

    public void decelerate(int amount) {
        if (gasolineLevel > 0) {
            if (amount > 0) {
                speed = Math.max(0, speed - amount);
                gasolineLevel = Math.max(0, gasolineLevel - 0.2);
            }
        } else {
            speed = 0;
        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setGasolineLevel(double gasolineLevel) {
        this.gasolineLevel = gasolineLevel;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return String.format("%s: speed=%.1f, gasoline=%.1f, tank=%.1f, maxSpeed=%.1f",
                typeName, speed, gasolineLevel, tankCapacity, maxSpeed);
    }
}