public class Shape {
    private String color;

    public Shape() {
        this.color = "Black";
    }

    public Shape(String color) {
        this.color = color;
    }

    public double calculateArea() {
        return 0;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("Shape[color=%s]", color);
    }
}