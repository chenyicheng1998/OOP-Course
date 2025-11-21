package view;

import controller.PetController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PetView extends Application {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private static final int PET_SIZE = 50;

    private Canvas canvas;
    private GraphicsContext gc;
    private PetController controller;
    private Image petImage;
    private AnimationTimer animationTimer;

    @Override
    public void init() {
        controller = new PetController(this);

        // Load pet image - using a simple emoji-style cat
        // You can replace this with any image file path
        try {
            petImage = new Image(getClass().getResourceAsStream("/pet.png"));
        } catch (Exception e) {
            System.out.println("Could not load pet image, will use default shape");
            petImage = null;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Set up mouse event handlers
        canvas.setOnMouseMoved(event -> {
            controller.handleMouseMoved(event.getX(), event.getY());
        });

        canvas.setOnMouseExited(event -> {
            controller.handleMouseExited();
        });

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);

        primaryStage.setTitle("Virtual Pet");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initial draw
        updateCanvas(controller.getPetX(), controller.getPetY());

        // Start animation loop
        startAnimationLoop();
    }

    private void startAnimationLoop() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                controller.update();
            }
        };
        animationTimer.start();
    }

    public void updateCanvas(double petX, double petY) {
        // Clear canvas
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        // Draw grid for reference (optional)
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(0.5);
        for (int i = 0; i < CANVAS_WIDTH; i += 50) {
            gc.strokeLine(i, 0, i, CANVAS_HEIGHT);
            gc.strokeLine(0, i, CANVAS_WIDTH, i);
        }

        // Draw pet
        if (petImage != null) {
            // Draw the pet image centered at (petX, petY)
            gc.drawImage(petImage, petX - PET_SIZE / 2, petY - PET_SIZE / 2, PET_SIZE, PET_SIZE);
        } else {
            // Draw a default cute pet shape (a simple cat face)
            drawDefaultPet(petX, petY);
        }
    }

    private void drawDefaultPet(double x, double y) {
        // Draw a cute cat face as default
        double size = PET_SIZE;

        // Head (circle)
        gc.setFill(Color.ORANGE);
        gc.fillOval(x - size / 2, y - size / 2, size, size);

        // Ears (triangles)
        gc.setFill(Color.DARKORANGE);
        // Left ear
        gc.fillPolygon(
                new double[]{x - size / 3, x - size / 2.5, x - size / 6},
                new double[]{y - size / 2, y - size / 1.2, y - size / 2.5},
                3
        );
        // Right ear
        gc.fillPolygon(
                new double[]{x + size / 3, x + size / 2.5, x + size / 6},
                new double[]{y - size / 2, y - size / 1.2, y - size / 2.5},
                3
        );

        // Eyes
        gc.setFill(Color.BLACK);
        gc.fillOval(x - size / 5, y - size / 8, size / 8, size / 6);
        gc.fillOval(x + size / 10, y - size / 8, size / 8, size / 6);

        // Nose
        gc.fillOval(x - size / 20, y + size / 10, size / 10, size / 10);

        // Mouth (arc)
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeArc(x - size / 6, y + size / 10, size / 3, size / 5, 180, 180, javafx.scene.shape.ArcType.OPEN);
    }

    @Override
    public void stop() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }
}

