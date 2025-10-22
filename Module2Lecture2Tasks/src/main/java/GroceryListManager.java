import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroceryListManager {
    // Use inner classes to store product information
    private static class GroceryItem {
        private String name;
        private double cost;
        private String category;
        private int quantity;

        public GroceryItem(String name, double cost, String category, int quantity) {
            this.name = name;
            this.cost = cost;
            this.category = category;
            this.quantity = quantity;
        }

        // Getters and setters
        public String getName() { return name; }
        public double getCost() { return cost; }
        public String getCategory() { return category; }
        public int getQuantity() { return quantity; }

        public void setCost(double cost) { this.cost = cost; }
        public void setCategory(String category) { this.category = category; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        @Override
        public String toString() {
            return String.format("%s (%.2f€, %d pcs) - %s", name, cost, quantity, category);
        }
    }

    private ArrayList<GroceryItem> groceryList = new ArrayList<>();

    // Task 1: Basic Methods
    public void addItem(String item) {
        addItem(item, 0.0, "Uncategorized", 1);
    }

    public void removeItem(String item) {
        GroceryItem toRemove = findItem(item);
        if (toRemove != null) {
            groceryList.remove(toRemove);
            System.out.println("Removed '" + item + "' from the list.");
        } else {
            System.out.println("Item '" + item + "' not found in the list.");
        }
    }

    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
            return;
        }

        System.out.println("Grocery List:");
        for (int i = 0; i < groceryList.size(); i++) {
            GroceryItem item = groceryList.get(i);
            System.out.println((i + 1) + ". " + item);
        }
        System.out.println();
    }

    public boolean checkItem(String item) {
        return findItem(item) != null;
    }

    // Task 2: Add costs and calculate the total cost
    public void addItem(String item, double cost) {
        addItem(item, cost, "Uncategorized", 1);
    }

    // Add an overloaded method to support cost + quantity
    public void addItem(String item, double cost, int quantity) {
        addItem(item, cost, "Uncategorized", quantity);
    }

    public double calculateTotalCost() {
        double total = 0.0;
        for (GroceryItem item : groceryList) {
            total += item.getCost() * item.getQuantity();
        }
        return total;
    }

    // Task 3: Add classification functionality
    public void addItem(String item, double cost, String category) {
        addItem(item, cost, category, 1);
    }

    public void displayByCategory(String category) {
        System.out.println("Items in category '" + category + "':");
        boolean found = false;
        for (int i = 0; i < groceryList.size(); i++) {
            GroceryItem item = groceryList.get(i);
            if (item.getCategory().equalsIgnoreCase(category)) {
                System.out.println((i + 1) + ". " + item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found in category '" + category + "'.");
        }
        System.out.println();
    }

    // Task 4: Add quantity tracking
    public void addItem(String item, double cost, String category, int quantity) {
        // Check if there is already a product with the same name
        GroceryItem existingItem = findItem(item);
        if (existingItem != null) {
            System.out.println("Item '" + item + "' already exists in the list. Updating quantity.");
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            groceryList.add(new GroceryItem(item, cost, category, quantity));
            System.out.println("Added '" + item + "' to the list.");
        }
    }

    public void updateQuantity(String item, int newQuantity) {
        GroceryItem existingItem = findItem(item);
        if (existingItem != null) {
            if (newQuantity >= 0) {
                existingItem.setQuantity(newQuantity);
                System.out.println("Updated quantity of '" + item + "' to " + newQuantity + ".");
            } else {
                System.out.println("Quantity cannot be negative.");
            }
        } else {
            System.out.println("Item '" + item + "' not found in the list.");
        }
    }

    public void displayAvailableItems() {
        System.out.println("Available Items (with positive quantity):");
        boolean found = false;
        for (int i = 0; i < groceryList.size(); i++) {
            GroceryItem item = groceryList.get(i);
            if (item.getQuantity() > 0) {
                System.out.println((i + 1) + ". " + item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available items with positive quantity.");
        }
        System.out.println();
    }

    // Auxiliary method: Search for the product
    private GroceryItem findItem(String itemName) {
        for (GroceryItem item : groceryList) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // Task 5: Test all functions
    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();

        System.out.println("=== GROCERY LIST MANAGER DEMONSTRATION ===\n");

        // Test Task 1: Basic Functions
        System.out.println("1. TESTING BASIC FUNCTIONALITY");
        System.out.println("-----------------------------");
        manager.addItem("Apples");
        manager.addItem("Milk");
        manager.addItem("Bread");
        manager.displayList();

        System.out.println("Is 'Milk' in the grocery list? " + manager.checkItem("Milk"));
        System.out.println("Is 'Butter' in the grocery list? " + manager.checkItem("Butter"));

        manager.removeItem("Milk");
        manager.displayList();

        // Clear the list for retesting
        manager = new GroceryListManager();

        // Test Task 2: Cost Calculation
        System.out.println("\n2. TESTING COST CALCULATION");
        System.out.println("---------------------------");
        manager.addItem("Apples", 2.50);
        manager.addItem("Milk", 1.20);
        manager.addItem("Bread", 1.80, 2); // Use the new overloading method: 2 breads
        manager.displayList();
        System.out.printf("Total cost: %.2f€\n\n", manager.calculateTotalCost());

        // Test Task 3: Classification Function
        System.out.println("3. TESTING CATEGORIES");
        System.out.println("---------------------");
        manager.addItem("Bananas", 1.50, "Fruits", 3);
        manager.addItem("Yogurt", 0.80, "Dairy", 4);
        manager.addItem("Croissant", 1.10, "Bakery", 2);
        manager.addItem("Orange Juice", 2.20, "Beverages", 1);

        manager.displayByCategory("Fruits");
        manager.displayByCategory("Dairy");
        manager.displayByCategory("Bakery");
        manager.displayByCategory("Electronics"); // Non-existent classification

        // Test Task 4: Quantity Tracking
        System.out.println("4. TESTING QUANTITY TRACKING");
        System.out.println("----------------------------");
        manager.displayAvailableItems();

        // Update Quantity
        manager.updateQuantity("Apples", 5);
        manager.updateQuantity("Milk", 0); // Set to 0
        manager.updateQuantity("NonExistent", 10); // Non-existent item

        manager.displayAvailableItems();

        // Final display and total cost calculation
        System.out.println("5. FINAL SUMMARY");
        System.out.println("----------------");
        manager.displayList();
        System.out.printf("Final total cost: %.2f€\n", manager.calculateTotalCost());

        // Test the boundary situation
        System.out.println("\n6. TESTING EDGE CASES");
        System.out.println("---------------------");
        manager.removeItem("NonExistentItem"); // Remove non-existent items
        manager.updateQuantity("Apples", -5); // Negative quantity
        manager.addItem("Apples", 3.00, "Fruits", 2); // Add existing item (the quantity should be updated)
        manager.displayList();
        System.out.printf("Updated total cost: %.2f€\n", manager.calculateTotalCost());
    }
}