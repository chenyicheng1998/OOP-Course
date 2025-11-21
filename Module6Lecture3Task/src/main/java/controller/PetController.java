package controller;

import model.Pet;
import view.PetView;

public class PetController {
    private Pet pet;
    private PetView view;

    public PetController(PetView view) {
        this.view = view;
        // Initialize pet at center of canvas
        this.pet = new Pet(300, 300);
    }

    public void handleMouseMoved(double mouseX, double mouseY) {
        pet.setTarget(mouseX, mouseY);
    }

    public void handleMouseExited() {
        pet.stopMoving();
    }

    public void update() {
        pet.update();
        view.updateCanvas(pet.getX(), pet.getY());
    }

    public double getPetX() {
        return pet.getX();
    }

    public double getPetY() {
        return pet.getY();
    }

    public boolean isPetMoving() {
        return pet.isMoving();
    }
}

