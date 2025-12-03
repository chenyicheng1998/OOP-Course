import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PalindromeChecker.
 * Tests various cases of palindromes and non-palindromes:
 * - Simple palindromes (e.g., "radar")
 * - Palindromes with mixed case (e.g., "Racecar")
 * - Palindromes with spaces and punctuation (e.g., "A man, a plan, a canal, Panama")
 * - Non-palindromes (e.g., "hello")
 * - Edge cases (empty string, single character, null)
 */
public class PalindromeCheckerTest {

    private PalindromeChecker checker;

    @BeforeEach
    void setUp() {
        checker = new PalindromeChecker();
    }

    @Test
    @DisplayName("Test simple palindrome - radar")
    void testSimplePalindrome() {
        assertTrue(checker.isPalindrome("radar"), "radar should be a palindrome");
    }

    @Test
    @DisplayName("Test palindrome with mixed case")
    void testPalindromeWithMixedCase() {
        assertTrue(checker.isPalindrome("Racecar"), "Racecar should be a palindrome");
    }

    @Test
    @DisplayName("Test palindrome with spaces and punctuation")
    void testPalindromeWithSpacesAndPunctuation() {
        assertTrue(checker.isPalindrome("A man, a plan, a canal, Panama"),
                "A man, a plan, a canal, Panama should be a palindrome");
    }

    @Test
    @DisplayName("Test non-palindrome - hello")
    void testNonPalindrome() {
        assertFalse(checker.isPalindrome("hello"), "hello should not be a palindrome");
    }

    @Test
    @DisplayName("Test non-palindrome - openai")
    void testNonPalindromeOpenAI() {
        assertFalse(checker.isPalindrome("openai"), "openai should not be a palindrome");
    }

    @Test
    @DisplayName("Test empty string")
    void testEmptyString() {
        assertTrue(checker.isPalindrome(""), "Empty string should be considered a palindrome");
    }

    @Test
    @DisplayName("Test single character")
    void testSingleCharacter() {
        assertTrue(checker.isPalindrome("a"), "Single character should be a palindrome");
    }

    @Test
    @DisplayName("Test null string")
    void testNullString() {
        assertFalse(checker.isPalindrome(null), "Null should not be a palindrome");
    }

    @Test
    @DisplayName("Test palindrome with numbers")
    void testPalindromeWithNumbers() {
        assertTrue(checker.isPalindrome("12321"), "12321 should be a palindrome");
    }

    @Test
    @DisplayName("Test palindrome - Was it a car or a cat I saw")
    void testComplexPalindrome() {
        assertTrue(checker.isPalindrome("Was it a car or a cat I saw"),
                "Was it a car or a cat I saw should be a palindrome");
    }

    @Test
    @DisplayName("Test palindrome with only spaces")
    void testOnlySpaces() {
        assertTrue(checker.isPalindrome("   "), "String with only spaces should be a palindrome");
    }
}

