package model;

public class Pet {
    private double x;
    private double y;
    private double targetX;
    private double targetY;
    private boolean isMoving;
    private static final double SPEED = 2.0; // Pixels per update
    private static final double STOP_DISTANCE = 5.0; // Stop when within this distance

    public Pet(double x, double y) {
        this.x = x;
        this.y = y;
        this.targetX = x;
        this.targetY = y;
        this.isMoving = false;
    }

    public void setTarget(double targetX, double targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.isMoving = true;
    }

    public void stopMoving() {
        this.isMoving = false;
    }

    public void update() {
        if (!isMoving) {
            return;
        }

        // Calculate distance to target
        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Stop if close enough to target
        if (distance < STOP_DISTANCE) {
            x = targetX;
            y = targetY;
            isMoving = false;
            return;
        }

        // Move towards target
        double moveX = (dx / distance) * SPEED;
        double moveY = (dy / distance) * SPEED;

        x += moveX;
        y += moveY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isMoving() {
        return isMoving;
    }
}

