public class PalindromeChecker {

    public static void main(String[] args) {

        // Step 1: Hardcoded string
        String word = "madam";

        // Step 2: Reverse string using simple loop
        String reversed = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed += word.charAt(i);
        }

        // Step 3: Conditional check
        if (word.equals(reversed)) {
            System.out.println("The hardcoded string \"" + word + "\" is a Palindrome.");
        } else {
            System.out.println("The hardcoded string \"" + word + "\" is NOT a Palindrome.");
        }
    }
}