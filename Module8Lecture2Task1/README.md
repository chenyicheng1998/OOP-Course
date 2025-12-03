# Module 8 Lecture 2 Task 1 - Palindrome Checker (TDD)

## Task Description

Create a `PalindromeChecker` class following Test-Driven Development (TDD) principles to determine if a given string is
a palindrome.

## What is a Palindrome?

A palindrome is a word, phrase, number, or other sequence of characters that reads the same forward and backward,
ignoring:

- Spaces
- Punctuation
- Case differences

## TDD Approach Used

### Step 1: Write Tests First ✅

Created `PalindromeCheckerTest` with 11 comprehensive test cases **before** implementing the class.

### Step 2: Run Tests (They Should Fail) ✅

Initially, tests fail because the implementation doesn't exist yet.

### Step 3: Implement Minimum Code to Pass Tests ✅

Implemented `PalindromeChecker` class with `isPalindrome()` method.

### Step 4: Run Tests Again (They Should Pass) ✅

All 11 tests pass successfully!

### Step 5: Refactor (If Needed) ✅

Code is clean and efficient - no refactoring needed.

## Implementation Details

### PalindromeChecker Class

**Method**: `boolean isPalindrome(String str)`

**Algorithm**:

1. Handle null case (return false)
2. Remove all non-alphanumeric characters
3. Convert to lowercase
4. Compare characters from both ends moving towards center
5. Return true if all characters match

### Test Coverage (11 tests)

| Test Case         | Description                      | Expected Result |
|-------------------|----------------------------------|-----------------|
| Simple palindrome | "radar"                          | true            |
| Mixed case        | "Racecar"                        | true            |
| With punctuation  | "A man, a plan, a canal, Panama" | true            |
| Non-palindrome    | "hello"                          | false           |
| Non-palindrome    | "openai"                         | false           |
| Empty string      | ""                               | true            |
| Single character  | "a"                              | true            |
| Null string       | null                             | false           |
| With numbers      | "12321"                          | true            |
| Complex phrase    | "Was it a car or a cat I saw"    | true            |
| Only spaces       | "   "                            | true            |

## Running the Tests

```bash
cd Module8Lecture2Task1
mvn clean test
```

## Test Results

```
Tests run: 11
Failures: 0
Errors: 0
Skipped: 0
✅ BUILD SUCCESS
```

## Key Learning Points

1. **TDD Cycle**: Write test → Run (fail) → Implement → Run (pass) → Refactor
2. **Test First**: Writing tests before implementation helps define requirements
3. **Comprehensive Coverage**: Tests cover normal cases, edge cases, and error cases
4. **Clean Code**: Implementation is simple and focused on passing tests
5. **Documentation**: Clear JavaDoc comments explain the purpose and behavior

## Features

✅ Handles mixed case (case-insensitive)  
✅ Ignores spaces and punctuation  
✅ Works with numbers  
✅ Handles null and empty strings gracefully  
✅ Efficient two-pointer algorithm  
✅ Comprehensive test coverage  
✅ Full JavaDoc documentation

## Assignment Completion

✅ **Task 1 Completed: 3/3 points**

- Tests written first (TDD approach)
- All tests pass
- Clean implementation
- Proper documentation

---

**Status**: ✅ COMPLETE - Following TDD principles successfully!

