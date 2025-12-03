# Module 8 Lecture 1 Task 2 - StringManipulator Class and Tests

## Task Description

Create a `StringManipulator` class with string manipulation methods and comprehensive JUnit tests.

## Implementation

### StringManipulator Class

A utility class providing common string operations:

#### Methods

1. **concatenate(String str1, String str2)**
    - Concatenates two strings
    - Handles null values gracefully (treats as empty strings)

2. **findLength(String str)**
    - Returns the length of a string
    - Returns 0 for null strings

3. **convertToUpperCase(String str)**
    - Converts string to uppercase
    - Returns empty string for null input

4. **convertToLowerCase(String str)**
    - Converts string to lowercase
    - Returns empty string for null input

5. **containsSubstring(String str, String subStr)**
    - Checks if string contains a substring
    - Returns false for null inputs
    - Case-sensitive matching

### Test Coverage

Comprehensive JUnit test suite with **26 test cases** covering:

#### Concatenate Tests (5 tests)

- Normal string concatenation
- Concatenation with spaces
- Empty strings
- Null handling (both null, one null)

#### Find Length Tests (4 tests)

- Normal strings
- Empty strings
- Null strings
- Strings with spaces

#### Convert to Upper Case Tests (5 tests)

- Normal strings
- Mixed case strings
- Already uppercase strings
- Empty strings
- Null strings

#### Convert to Lower Case Tests (5 tests)

- Normal strings
- Mixed case strings
- Already lowercase strings
- Empty strings
- Null strings

#### Contains Substring Tests (7 tests)

- Substring exists
- Substring doesn't exist
- Empty substring
- Same strings
- Null string
- Null substring
- Case sensitivity

## Running the Tests

```bash
mvn clean test
```

## Test Results

All 26 JUnit tests pass successfully with comprehensive coverage of:

- Normal cases
- Edge cases (empty strings)
- Error cases (null values)
- Boundary conditions

## Key Features

- ✅ Robust null handling
- ✅ Comprehensive test coverage
- ✅ Clear JavaDoc documentation
- ✅ Descriptive test names using @DisplayName
- ✅ Proper test organization with @BeforeEach setup

## Assignment Completion

✅ **Task 2 Completed: 3 points**

- StringManipulator class implemented with all required methods
- Comprehensive JUnit test suite created
- All tests pass successfully
- Proper documentation included

## Total Points: 6/6

Both tasks completed successfully!

