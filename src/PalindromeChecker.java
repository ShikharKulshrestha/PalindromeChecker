
import java.util.Stack;
import java.util.*;

// Palindrome service class
public static class PalindromeChecker {

    // Public method to check palindrome
    public boolean checkPalindrome(String input) {

        if (input == null || input.isEmpty()) {
            return false; // Empty string not considered
        }

        // Convert input to lowercase for consistency
        input = input.toLowerCase();

        // Using Stack internally
        Stack<Character> stack = new Stack<>();

        // Push all characters into the stack
        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        // Compare original and reversed via stack
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != stack.pop()) {
                return false;
            }
        }

        return true;
    }
}


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PalindromeChecker checker = new PalindromeChecker(); // Encapsulated service

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        boolean result = checker.checkPalindrome(input);

        if (result) {
            System.out.println("The string is a Palindrome.");
        } else {
            System.out.println("The string is NOT a Palindrome.");
        }

        scanner.close();
    }
