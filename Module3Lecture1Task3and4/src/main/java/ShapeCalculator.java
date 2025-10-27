public class ShapeCalculator {
    public static void main(String[] args) {

        // Create an array of Shape objects (polymorphism)
        Shape[] shapes = {
                new Circle(5.0, "Red"),
                new Rectangle(4.0, 6.0, "Blue"),
                new Triangle(3.0, 8.0, "Green"),
                new Circle(3.5, "Yellow"),
                new Rectangle(2.5, 4.5, "Purple")
        };

        // Additional demonstration with specific shape types
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                System.out.printf("Area of Circle with radius %.1f (%s): %.2f\n",
                        circle.getRadius(), circle.getColor(), circle.calculateArea());
            } else if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                System.out.printf("Area of Rectangle with width %.1f and height %.1f (%s): %.2f\n",
                        rectangle.getWidth(), rectangle.getHeight(),
                        rectangle.getColor(), rectangle.calculateArea());
            } else if (shape instanceof Triangle) {
                Triangle triangle = (Triangle) shape;
                System.out.printf("Area of Triangle with base %.1f and height %.1f (%s): %.2f\n",
                        triangle.getBase(), triangle.getHeight(),
                        triangle.getColor(), triangle.calculateArea());
            }
        }
    }
}