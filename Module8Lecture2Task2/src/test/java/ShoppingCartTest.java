import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ShoppingCart.
 * Tests various shopping cart operations:
 * - Adding items to the cart
 * - Removing items from the cart
 * - Calculating total cost
 * - Getting item count
 * - Edge cases (removing non-existent items, empty cart, etc.)
 */
public class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    @DisplayName("Test adding items to cart")
    void testAddItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);

        assertEquals(2, cart.getItemCount(), "Cart should contain 2 items");
    }

    @Test
    @DisplayName("Test removing item from cart")
    void testRemoveItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.removeItem("Apple");

        assertEquals(1, cart.getItemCount(), "Cart should contain 1 item after removal");
    }

    @Test
    @DisplayName("Test calculating total cost")
    void testCalculateTotal() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.addItem("Orange", 0.75);

        assertEquals(2.25, cart.calculateTotal(), 0.01, "Total should be 2.25");
    }

    @Test
    @DisplayName("Test empty cart has zero items")
    void testEmptyCart() {
        assertEquals(0, cart.getItemCount(), "Empty cart should have 0 items");
    }

    @Test
    @DisplayName("Test empty cart has zero total")
    void testEmptyCartTotal() {
        assertEquals(0.0, cart.calculateTotal(), 0.01, "Empty cart should have 0.0 total");
    }

    @Test
    @DisplayName("Test removing non-existent item")
    void testRemoveNonExistentItem() {
        cart.addItem("Apple", 1.0);
        cart.removeItem("Banana");

        assertEquals(1, cart.getItemCount(), "Cart should still have 1 item");
    }

    @Test
    @DisplayName("Test adding multiple items with same name")
    void testAddMultipleSameItems() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Apple", 1.0);

        assertEquals(2, cart.getItemCount(), "Cart should contain 2 items");
        assertEquals(2.0, cart.calculateTotal(), 0.01, "Total should be 2.0");
    }

    @Test
    @DisplayName("Test adding item with zero price")
    void testAddItemWithZeroPrice() {
        cart.addItem("Free Sample", 0.0);

        assertEquals(1, cart.getItemCount(), "Cart should contain 1 item");
        assertEquals(0.0, cart.calculateTotal(), 0.01, "Total should be 0.0");
    }

    @Test
    @DisplayName("Test removing all items")
    void testRemoveAllItems() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);

        cart.removeItem("Apple");
        cart.removeItem("Banana");

        assertEquals(0, cart.getItemCount(), "Cart should be empty");
        assertEquals(0.0, cart.calculateTotal(), 0.01, "Total should be 0.0");
    }

    @Test
    @DisplayName("Test adding item with negative price")
    void testAddItemWithNegativePrice() {
        cart.addItem("Discount Item", -1.0);

        assertEquals(1, cart.getItemCount(), "Cart should contain 1 item");
        assertEquals(-1.0, cart.calculateTotal(), 0.01, "Total should be -1.0 (discount)");
    }

    @Test
    @DisplayName("Test large number of items")
    void testLargeNumberOfItems() {
        for (int i = 0; i < 100; i++) {
            cart.addItem("Item" + i, 1.0);
        }

        assertEquals(100, cart.getItemCount(), "Cart should contain 100 items");
        assertEquals(100.0, cart.calculateTotal(), 0.01, "Total should be 100.0");
    }

    @Test
    @DisplayName("Test precision in total calculation")
    void testPrecisionInCalculation() {
        cart.addItem("Item1", 0.1);
        cart.addItem("Item2", 0.2);
        cart.addItem("Item3", 0.3);

        assertEquals(0.6, cart.calculateTotal(), 0.001, "Total should be 0.6");
    }
}

