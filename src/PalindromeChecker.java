import java.util.Scanner;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

interface PalindromeStrategy {
    boolean check(String word);
    String getStrategyName();
}

class StackStrategy implements PalindromeStrategy {

    @Override
    public boolean check(String word) {
        String normalized = word.toLowerCase().replaceAll("[^a-z0-9]", "");
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < normalized.length(); i++) {
            stack.push(normalized.charAt(i));
        }

        String stackReversed = "";
        while (!stack.isEmpty()) {
            stackReversed = stackReversed + stack.pop();
        }

        return normalized.equals(stackReversed);
    }

    @Override
    public String getStrategyName() {
        return "Stack Strategy";
    }
}

class DequeStrategy implements PalindromeStrategy {

    @Override
    public boolean check(String word) {
        String normalized = word.toLowerCase().replaceAll("[^a-z0-9]", "");
        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < normalized.length(); i++) {
            deque.offerLast(normalized.charAt(i));
        }

        while (deque.size() > 1) {
            if (deque.pollFirst() != deque.pollLast()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String getStrategyName() {
        return "Deque Strategy";
    }
}

class RecursiveStrategy implements PalindromeStrategy {

    private boolean isPalindrome(String word, int start, int end) {
        if (start >= end) return true;
        if (word.charAt(start) != word.charAt(end)) return false;
        return isPalindrome(word, start + 1, end - 1);
    }

    @Override
    public boolean check(String word) {
        String normalized = word.toLowerCase().replaceAll("[^a-z0-9]", "");
        return isPalindrome(normalized, 0, normalized.length() - 1);
    }

    @Override
    public String getStrategyName() {
        return "Recursive Strategy";
    }
}

class PalindromeContext {

    private PalindromeStrategy strategy;

    public PalindromeContext(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean executeStrategy(String word) {
        return strategy.check(word);
    }

    public String getStrategyName() {
        return strategy.getStrategyName();
    }
}

class PalindromeCheckerApp {

    public static void main(String[] args) {
        System.out.println("--- UC12: Strategy Pattern for Palindrome Algorithms ---");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input Text: ");
        String word = scanner.nextLine();

        PalindromeContext context = new PalindromeContext(new StackStrategy());
        System.out.println("Strategy            : " + context.getStrategyName());
        System.out.println("Input Text          : " + word);
        System.out.println("Is it a Palindrome? : " + context.executeStrategy(word));
        System.out.println();

        context.setStrategy(new DequeStrategy());
        System.out.println("Strategy            : " + context.getStrategyName());
        System.out.println("Input Text          : " + word);
        System.out.println("Is it a Palindrome? : " + context.executeStrategy(word));
        System.out.println();

        context.setStrategy(new RecursiveStrategy());
        System.out.println("Strategy            : " + context.getStrategyName());
        System.out.println("Input Text          : " + word);
        System.out.println("Is it a Palindrome? : " + context.executeStrategy(word));

        scanner.close();
    }
}