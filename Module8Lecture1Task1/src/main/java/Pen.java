/**
 * Represents a pen that can draw in different colors.
 * The pen has a cap that must be removed before drawing.
 *
 * @author Yicheng CHEN
 * @version 1.0
 */
public class Pen {
    /**
     * Enum representing the available colors for the pen.
     */
    public enum Color {
        RED("red"), GREEN("green"), BLUE("blue");
        private final String color;

        Color(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return color;
        }
    }

    private Color currentColor;
    private boolean capOn;

    /**
     * Creates a new pen with default red color and cap on.
     */
    public Pen() {
        this.currentColor = Color.RED;
        this.capOn = true;
    }

    /**
     * Creates a new pen with specified color and cap on.
     *
     * @param color The initial color of the pen
     */
    public Pen(Color color) {
        this.currentColor = color;
        this.capOn = true;
    }

    /**
     * Removes the cap from the pen, allowing it to draw.
     */
    public void capOff() {
        this.capOn = false;
    }

    /**
     * Puts the cap back on the pen, preventing it from drawing.
     */
    public void capOn() {
        this.capOn = true;
    }

    /**
     * Attempts to draw with the pen.
     *
     * @return A string indicating the drawing action if cap is off,
     * or empty string if cap is on
     */
    public String draw() {
        if (capOn) {
            return "";
        }
        return "Drawing " + currentColor.toString();
    }

    /**
     * Changes the color of the pen. The color can only be changed when the cap is on.
     *
     * @param newColor The new color to set
     */
    public void changeColor(Color newColor) {
        if (capOn) {
            this.currentColor = newColor;
        }
    }
}

