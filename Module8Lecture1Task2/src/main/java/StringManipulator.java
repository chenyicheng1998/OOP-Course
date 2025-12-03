/**
 * A utility class for manipulating strings.
 * Provides methods for common string operations like concatenation,
 * case conversion, and substring checking.
 *
 * @author Yicheng CHEN
 * @version 1.0
 */
public class StringManipulator {

    /**
     * Concatenates two strings together.
     *
     * @param str1 The first string
     * @param str2 The second string
     * @return The concatenated result of str1 and str2
     */
    public String concatenate(String str1, String str2) {
        if (str1 == null) str1 = "";
        if (str2 == null) str2 = "";
        return str1 + str2;
    }

    /**
     * Finds and returns the length of a string.
     *
     * @param str The string to measure
     * @return The length of the string, or 0 if the string is null
     */
    public int findLength(String str) {
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    /**
     * Converts a string to uppercase.
     *
     * @param str The string to convert
     * @return The string in uppercase, or empty string if input is null
     */
    public String convertToUpperCase(String str) {
        if (str == null) {
            return "";
        }
        return str.toUpperCase();
    }

    /**
     * Converts a string to lowercase.
     *
     * @param str The string to convert
     * @return The string in lowercase, or empty string if input is null
     */
    public String convertToLowerCase(String str) {
        if (str == null) {
            return "";
        }
        return str.toLowerCase();
    }

    /**
     * Checks if a string contains a specific substring.
     *
     * @param str    The string to search in
     * @param subStr The substring to search for
     * @return true if the string contains the substring, false otherwise
     */
    public boolean containsSubstring(String str, String subStr) {
        if (str == null || subStr == null) {
            return false;
        }
        return str.contains(subStr);
    }
}

