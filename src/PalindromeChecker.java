public class PalindromeChecker {

    // Application Version Constant
    static final String APP_NAME = "Palindrome Checker App";
    static final String VERSION = "1.0";

    // Entry Point of the Application
    public static void main(String[] args) {

        displayWelcomeMessage();

        // After welcome, program can continue
        System.out.println("\nApplication is ready...");
        System.out.println("Proceeding to Palindrome Processing...\n");
    }

    // Method to display welcome message
    public static void displayWelcomeMessage() {
        System.out.println("=======================================");
        System.out.println("       " + APP_NAME);
        System.out.println("       Version: " + VERSION);
        System.out.println("=======================================");
        System.out.println("A console-based Java application");
        System.out.println("to validate palindromes using");
        System.out.println("multiple data structure approaches.");
        System.out.println("=======================================");
    }
}