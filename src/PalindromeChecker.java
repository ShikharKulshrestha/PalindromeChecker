import java.util.Scanner;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

interface PalindromeStrategy {
    boolean check(String word);
    String getStrategyName();
}

class StringBuilderStrategy implements PalindromeStrategy {

    @Override
    public boolean check(String word) {
        String normalized = word.toLowerCase().replaceAll("[^a-z0-9]", "");
        return normalized.equals(new StringBuilder(normalized).reverse().toString());
    }

    @Override
    public String getStrategyName() {
        return "StringBuilder Strategy";
    }
}

class TwoPointerStrategy implements PalindromeStrategy {

    @Override
    public boolean check(String word) {
        String normalized = word.toLowerCase().replaceAll("[^a-z0-9]", "");
        char[] chars = normalized.toCharArray();
        int start = 0;
        int end = chars.length - 1;

        while (start < end) {
            if (chars[start] != chars[end]) return false;
            start++;
            end--;
        }
        return true;
    }

    @Override
    public String getStrategyName() {
        return "Two Pointer Strategy";
    }
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
            if (deque.pollFirst() != deque.pollLast()) return false;
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

class UC13_PalindromeCheckerApp {

    static void measurePerformance(PalindromeStrategy strategy, String word) {
        long startTime = System.nanoTime();
        boolean result = strategy.check(word);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("Strategy            : " + strategy.getStrategyName());
        System.out.println("Is it a Palindrome? : " + result);
        System.out.println("Execution Time      : " + duration + " ns");
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("--- UC13: Performance Comparison ---");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input Text: ");
        String word = scanner.nextLine();

        System.out.println("Input Text          : " + word);
        System.out.println();

        measurePerformance(new StringBuilderStrategy(), word);
        measurePerformance(new TwoPointerStrategy(), word);
        measurePerformance(new StackStrategy(), word);
        measurePerformance(new DequeStrategy(), word);
        measurePerformance(new RecursiveStrategy(), word);

        scanner.close();
    }
}