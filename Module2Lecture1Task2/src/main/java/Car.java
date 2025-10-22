public class Car {
    private double speed;
    private double gasolineLevel;
    private String typeName;
    private double tankCapacity;
    private double maxSpeed;

    // 原有构造函数
    public Car(String typeName) {
        this.speed = 0;
        this.gasolineLevel = 0;
        this.typeName = typeName;
        this.tankCapacity = 100;
        this.maxSpeed = 200;
    }

    // 新增构造函数
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

    public String getTypeName() {
        return typeName;
    }

    public void fillTank() {
        gasolineLevel = tankCapacity;
    }

    public double getGasolineLevel() {
        return gasolineLevel;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}