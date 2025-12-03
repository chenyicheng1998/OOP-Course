import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StringManipulator.
 * Contains test cases for all string manipulation methods.
 */
class StringManipulatorTest {

    private StringManipulator manipulator;

    @BeforeEach
    void setUp() {
        manipulator = new StringManipulator();
    }

    @Test
    @DisplayName("Test concatenate with two non-empty strings")
    void testConcatenateNormalStrings() {
        String result = manipulator.concatenate("Hello", "World");
        assertEquals("HelloWorld", result);
    }

    @Test
    @DisplayName("Test concatenate with space between")
    void testConcatenateWithSpace() {
        String result = manipulator.concatenate("Hello ", "World");
        assertEquals("Hello World", result);
    }

    @Test
    @DisplayName("Test concatenate with empty strings")
    void testConcatenateEmptyStrings() {
        String result = manipulator.concatenate("", "");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test concatenate with null strings")
    void testConcatenateNullStrings() {
        String result = manipulator.concatenate(null, null);
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test concatenate with one null string")
    void testConcatenateOneNull() {
        String result1 = manipulator.concatenate("Hello", null);
        assertEquals("Hello", result1);

        String result2 = manipulator.concatenate(null, "World");
        assertEquals("World", result2);
    }

    @Test
    @DisplayName("Test findLength with normal string")
    void testFindLengthNormalString() {
        int length = manipulator.findLength("Hello");
        assertEquals(5, length);
    }

    @Test
    @DisplayName("Test findLength with empty string")
    void testFindLengthEmptyString() {
        int length = manipulator.findLength("");
        assertEquals(0, length);
    }

    @Test
    @DisplayName("Test findLength with null string")
    void testFindLengthNullString() {
        int length = manipulator.findLength(null);
        assertEquals(0, length);
    }

    @Test
    @DisplayName("Test findLength with string containing spaces")
    void testFindLengthWithSpaces() {
        int length = manipulator.findLength("Hello World");
        assertEquals(11, length);
    }

    @Test
    @DisplayName("Test convertToUpperCase with normal string")
    void testConvertToUpperCaseNormalString() {
        String result = manipulator.convertToUpperCase("hello");
        assertEquals("HELLO", result);
    }

    @Test
    @DisplayName("Test convertToUpperCase with mixed case")
    void testConvertToUpperCaseMixedCase() {
        String result = manipulator.convertToUpperCase("HeLLo WoRLd");
        assertEquals("HELLO WORLD", result);
    }

    @Test
    @DisplayName("Test convertToUpperCase with already uppercase")
    void testConvertToUpperCaseAlreadyUpper() {
        String result = manipulator.convertToUpperCase("HELLO");
        assertEquals("HELLO", result);
    }

    @Test
    @DisplayName("Test convertToUpperCase with empty string")
    void testConvertToUpperCaseEmptyString() {
        String result = manipulator.convertToUpperCase("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test convertToUpperCase with null string")
    void testConvertToUpperCaseNullString() {
        String result = manipulator.convertToUpperCase(null);
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test convertToLowerCase with normal string")
    void testConvertToLowerCaseNormalString() {
        String result = manipulator.convertToLowerCase("HELLO");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("Test convertToLowerCase with mixed case")
    void testConvertToLowerCaseMixedCase() {
        String result = manipulator.convertToLowerCase("HeLLo WoRLd");
        assertEquals("hello world", result);
    }

    @Test
    @DisplayName("Test convertToLowerCase with already lowercase")
    void testConvertToLowerCaseAlreadyLower() {
        String result = manipulator.convertToLowerCase("hello");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("Test convertToLowerCase with empty string")
    void testConvertToLowerCaseEmptyString() {
        String result = manipulator.convertToLowerCase("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test convertToLowerCase with null string")
    void testConvertToLowerCaseNullString() {
        String result = manipulator.convertToLowerCase(null);
        assertEquals("", result);
    }

    @Test
    @DisplayName("Test containsSubstring when substring exists")
    void testContainsSubstringExists() {
        boolean result = manipulator.containsSubstring("Hello World", "World");
        assertTrue(result);
    }

    @Test
    @DisplayName("Test containsSubstring when substring does not exist")
    void testContainsSubstringNotExists() {
        boolean result = manipulator.containsSubstring("Hello World", "Java");
        assertFalse(result);
    }

    @Test
    @DisplayName("Test containsSubstring with empty substring")
    void testContainsSubstringEmpty() {
        boolean result = manipulator.containsSubstring("Hello", "");
        assertTrue(result);
    }

    @Test
    @DisplayName("Test containsSubstring with same strings")
    void testContainsSubstringSameString() {
        boolean result = manipulator.containsSubstring("Hello", "Hello");
        assertTrue(result);
    }

    @Test
    @DisplayName("Test containsSubstring with null string")
    void testContainsSubstringNullString() {
        boolean result = manipulator.containsSubstring(null, "Hello");
        assertFalse(result);
    }

    @Test
    @DisplayName("Test containsSubstring with null substring")
    void testContainsSubstringNullSubstring() {
        boolean result = manipulator.containsSubstring("Hello", null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Test containsSubstring case sensitive")
    void testContainsSubstringCaseSensitive() {
        boolean result1 = manipulator.containsSubstring("Hello World", "world");
        assertFalse(result1);

        boolean result2 = manipulator.containsSubstring("Hello World", "World");
        assertTrue(result2);
    }
}

