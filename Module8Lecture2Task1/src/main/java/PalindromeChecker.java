/**
 * A class to check if a given string is a palindrome.
 * A palindrome is a word, phrase, number, or other sequence of characters
 * that reads the same forward and backward (ignoring spaces, punctuation, and capitalization).
 *
 * @author Student
 * @version 1.0
 */
public class PalindromeChecker {

    /**
     * Checks if the given string is a palindrome.
     * Ignores spaces, punctuation, and case differences.
     *
     * @param str the string to check
     * @return true if the string is a palindrome, false otherwise
     */
    public boolean isPalindrome(String str) {
        // Handle null case
        if (str == null) {
            return false;
        }

        // Remove all non-alphanumeric characters and convert to lowercase
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Empty string or single character is considered a palindrome
        if (cleaned.length() <= 1) {
            return true;
        }

        // Check if the cleaned string is the same as its reverse
        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

