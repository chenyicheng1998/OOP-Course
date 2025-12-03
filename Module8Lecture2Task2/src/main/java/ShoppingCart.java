import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a shopping cart that can manage items and calculate total cost.
 * Supports adding items, removing items, and calculating the total cost of all items.
 *
 * @author Student
 * @version 1.0
 */
public class ShoppingCart {

    /**
     * Inner class to represent an item in the shopping cart.
     */
    private static class Item {
        private String name;
        private double price;

        /**
         * Creates a new item.
         *
         * @param name  the name of the item
         * @param price the price of the item
         */
        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }

        /**
         * Gets the name of the item.
         *
         * @return the item name
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the price of the item.
         *
         * @return the item price
         */
        public double getPrice() {
            return price;
        }
    }

    private List<Item> items;

    /**
     * Creates a new empty shopping cart.
     */
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds an item to the shopping cart.
     *
     * @param name  the name of the item
     * @param price the price of the item
     */
    public void addItem(String name, double price) {
        items.add(new Item(name, price));
    }

    /**
     * Removes the first occurrence of an item with the given name from the cart.
     * If the item is not found, the cart remains unchanged.
     *
     * @param name the name of the item to remove
     */
    public void removeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
                return; // Remove only the first occurrence
            }
        }
    }

    /**
     * Gets the number of items in the shopping cart.
     *
     * @return the number of items
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Calculates the total cost of all items in the shopping cart.
     *
     * @return the total cost
     */
    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

