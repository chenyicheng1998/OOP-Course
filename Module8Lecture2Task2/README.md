# Module 8 Lecture 2 Task 2 - Shopping Cart (TDD)

## Task Description

Create a `ShoppingCart` class following Test-Driven Development (TDD) principles to manage items in a shopping cart.

## TDD Approach Used

### Step 1: Write Tests First ✅

Created `ShoppingCartTest` with 12 comprehensive test cases **before** implementing the class.

### Step 2: Run Tests (They Should Fail) ✅

Initially, tests fail because the implementation doesn't exist yet.

### Step 3: Implement Minimum Code to Pass Tests ✅

Implemented `ShoppingCart` class with required methods.

### Step 4: Run Tests Again (They Should Pass) ✅

All 12 tests pass successfully!

### Step 5: Refactor (If Needed) ✅

Code is clean and well-structured with proper encapsulation.

## Implementation Details

### ShoppingCart Class

**Methods**:

1. `void addItem(String name, double price)` - Adds an item to the cart
2. `void removeItem(String name)` - Removes the first occurrence of an item
3. `int getItemCount()` - Returns the number of items in the cart
4. `double calculateTotal()` - Returns the total cost of all items

**Internal Structure**:

- Uses an `ArrayList` to store items
- Inner `Item` class encapsulates item data (name, price)
- Efficient operations with proper encapsulation

### Test Coverage (12 tests)

| Test Case           | Description                | Purpose                          |
|---------------------|----------------------------|----------------------------------|
| Add items           | Add 2 items                | Verify addItem() works           |
| Remove item         | Remove 1 of 2 items        | Verify removeItem() works        |
| Calculate total     | Add 3 items, check sum     | Verify calculateTotal() works    |
| Empty cart          | Check item count           | Verify initial state             |
| Empty cart total    | Check total cost           | Verify zero total for empty cart |
| Remove non-existent | Try to remove missing item | Test error handling              |
| Multiple same items | Add same item twice        | Verify duplicates allowed        |
| Zero price item     | Add free item              | Test edge case                   |
| Remove all items    | Clear entire cart          | Verify complete removal          |
| Negative price      | Add discount item          | Test negative values             |
| Large number        | Add 100 items              | Test scalability                 |
| Precision           | Add decimal prices         | Test floating-point accuracy     |

## Running the Tests

```bash
cd Module8Lecture2Task2
mvn clean test
```

## Test Results

```
Tests run: 12
Failures: 0
Errors: 0
Skipped: 0
✅ BUILD SUCCESS
```

## Key Features

### Functionality

✅ Add items with name and price  
✅ Remove items by name (first occurrence)  
✅ Get total item count  
✅ Calculate total cost

### Robustness

✅ Handles empty cart  
✅ Handles non-existent item removal  
✅ Supports duplicate items  
✅ Works with zero and negative prices  
✅ Scales to large numbers of items  
✅ Maintains precision in calculations

### Code Quality

✅ Clean encapsulation with inner Item class  
✅ Full JavaDoc documentation  
✅ Efficient ArrayList implementation  
✅ Follows OOP principles

## Design Decisions

1. **ArrayList vs HashMap**: Used ArrayList to allow duplicate items with same name
2. **Inner Item Class**: Encapsulates item data, keeps implementation clean
3. **Remove First Occurrence**: When removing, only first matching item is removed
4. **Floating-Point**: Uses double for prices (suitable for this use case)

## Example Usage

```java
ShoppingCart cart = new ShoppingCart();

// Add items
cart.

addItem("Apple",1.0);
cart.

addItem("Banana",0.5);
cart.

addItem("Orange",0.75);

// Check count
System.out.

println(cart.getItemCount()); // Output: 3

// Calculate total
        System.out.

println(cart.calculateTotal()); // Output: 2.25

// Remove item
        cart.

removeItem("Banana");
System.out.

println(cart.getItemCount()); // Output: 2
        System.out.

println(cart.calculateTotal()); // Output: 1.75
```

## Assignment Completion

✅ **Task 2 Completed: 3/3 points**

- Tests written first (TDD approach)
- All tests pass
- Clean implementation with proper encapsulation
- Full documentation

---

**Status**: ✅ COMPLETE - Following TDD principles successfully!

