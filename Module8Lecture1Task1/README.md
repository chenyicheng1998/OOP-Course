# Module 8 Lecture 1 Task 1 - Pen Class Implementation

## Task Description

Design a `Pen` class based on provided JUnit tests that verify its functionality.

## Implementation

### Pen Class

The `Pen` class represents a pen with the following features:

- **Color Management**: Supports three colors (RED, GREEN, BLUE) via an enum
- **Cap Control**: Has methods to put the cap on (`capOn()`) and take it off (`capOff()`)
- **Drawing**: Can draw only when the cap is off
- **Color Change**: Color can only be changed when the cap is on (safety feature)

### Key Methods

1. `Pen()` - Creates a pen with default red color
2. `Pen(Color color)` - Creates a pen with specified color
3. `capOff()` - Removes the cap, allowing drawing
4. `capOn()` - Puts the cap back on, preventing drawing
5. `draw()` - Returns "Drawing [color]" if cap is off, empty string otherwise
6. `changeColor(Color newColor)` - Changes color only when cap is on

### Design Decisions

- The pen starts with the cap on by default (safe state)
- Drawing is only possible when the cap is off (realistic behavior)
- Color cannot be changed while drawing (prevents mess/accidents)
- All methods are designed to pass the provided unit tests

## Running the Tests

```bash
mvn clean test
```

## Test Results

All 3 JUnit tests pass successfully:

- `canDrawDefault` - Tests default behavior with red pen
- `canDrawSelectedColor` - Tests initialization with custom color
- `canChangeColor` - Tests color changing logic with cap state

## Assignment Completion

✅ **Task 1 Completed: 3 points**

- Pen class designed according to test specifications
- All JUnit tests pass
- Proper JavaDoc documentation included

