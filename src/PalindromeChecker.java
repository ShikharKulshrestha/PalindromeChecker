import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindrome(String input) {

        // Step 1: Normalize string
        input = input.toLowerCase();                 // Ignore case
        input = input.replaceAll("[^a-z0-9]", "");   // Remove spaces & special chars

        // Step 2: Two-pointer comparison
        int start = 0;
        int end = input.length() - 1;

        while (start < end) {
            if (input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        if (isPalindrome(input)) {
            System.out.println("The string is a Palindrome (Ignoring case & spaces).");
        } else {
            System.out.println("The string is NOT a Palindrome.");
        }

        scanner.close();
    }
}