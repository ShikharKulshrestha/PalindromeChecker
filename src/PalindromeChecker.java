import java.util.*;

public class PalindromeChecker {

    static class Node {
        char data;
        Node next;
        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {

        Usecase1();
        Usecase2();
        Usecase3();
        Usecase4();
        Usecase5();
        Usecase6();
        Usecase7();
        Usecase8();
        Usecase9();
        Usecase10();
        Usecase11();
        Usecase12();
        Usecase13();   // ✅ Performance Comparison
    }

    static void Usecase1() {
        System.out.println("Welcome to Palindrome Checker Management System");
        System.out.println("--------------------------------------");
    }

    static void Usecase2() {
        String input = "madam";
        boolean result = input.equals(new StringBuilder(input).reverse().toString());
        System.out.println("UC2 Result: " + result);
        System.out.println("--------------------------------------");
    }

    static void Usecase3() {
        String input = "level";
        int start = 0, end = input.length() - 1;
        boolean isPalindrome = true;

        while (start < end) {
            if (input.charAt(start++) != input.charAt(end--)) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println("UC3 Result: " + isPalindrome);
        System.out.println("--------------------------------------");
    }

    static void Usecase4() {
        String input = "radar";
        System.out.println("UC4 Result: " + input.equals(new StringBuilder(input).reverse()));
        System.out.println("--------------------------------------");
    }

    static void Usecase5() {
        String input = "refer";
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) stack.push(c);
        boolean result = true;
        for (char c : input.toCharArray())
            if (c != stack.pop()) result = false;

        System.out.println("UC5 Result: " + result);
        System.out.println("--------------------------------------");
    }

    static void Usecase6() {
        String input = "noon";
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            queue.add(c);
            stack.push(c);
        }
        boolean result = true;
        for (int i = 0; i < input.length(); i++)
            if (queue.remove() != stack.pop()) result = false;

        System.out.println("UC6 Result: " + result);
        System.out.println("--------------------------------------");
    }

    static void Usecase7() {
        String input = "civic";
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : input.toCharArray()) deque.addLast(c);
        boolean result = true;
        while (deque.size() > 1)
            if (deque.removeFirst() != deque.removeLast()) result = false;

        System.out.println("UC7 Result: " + result);
        System.out.println("--------------------------------------");
    }

    static void Usecase8() {
        String input = "level";
        Node head = null, tail = null;
        for (char c : input.toCharArray()) {
            Node n = new Node(c);
            if (head == null) head = tail = n;
            else { tail.next = n; tail = n; }
        }
        System.out.println("UC8 Executed");
        System.out.println("--------------------------------------");
    }

    static void Usecase9() {
        String input = "madam";
        System.out.println("UC9 Result: " + check(input, 0, input.length() - 1));
        System.out.println("--------------------------------------");
    }

    static boolean check(String s, int start, int end) {
        if (start >= end) return true;
        if (s.charAt(start) != s.charAt(end)) return false;
        return check(s, start + 1, end - 1);
    }

    static void Usecase10() {
        String input = "A man a plan a canal Panama";
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        System.out.println("UC10 Result: " +
                normalized.equals(new StringBuilder(normalized).reverse().toString()));
        System.out.println("--------------------------------------");
    }

    static void Usecase11() {
        PalindromeService service = new PalindromeService();
        System.out.println("UC11 Result: " + service.checkPalindrome("racecar"));
        System.out.println("--------------------------------------");
    }

    static void Usecase12() {
        PalindromeStrategy strategy = new StackStrategy();
        System.out.println("UC12 Result: " + strategy.check("level"));
        System.out.println("--------------------------------------");
    }

    // ================= UC13 =================
    static void Usecase13() {

        String input = "A man a plan a canal Panama";
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        System.out.println("UC13 Performance Comparison");
        System.out.println("Input Length: " + normalized.length());

        // Two Pointer
        long start = System.nanoTime();
        boolean result1 = twoPointerCheck(normalized);
        long end = System.nanoTime();
        long time1 = end - start;

        // Stack Strategy
        PalindromeStrategy stackStrategy = new StackStrategy();
        start = System.nanoTime();
        boolean result2 = stackStrategy.check(normalized);
        end = System.nanoTime();
        long time2 = end - start;

        // Deque Strategy
        PalindromeStrategy dequeStrategy = new DequeStrategy();
        start = System.nanoTime();
        boolean result3 = dequeStrategy.check(normalized);
        end = System.nanoTime();
        long time3 = end - start;

        System.out.println("Two Pointer Time: " + time1 + " ns");
        System.out.println("Stack Strategy Time: " + time2 + " ns");
        System.out.println("Deque Strategy Time: " + time3 + " ns");
        System.out.println("--------------------------------------");
    }

    static boolean twoPointerCheck(String input) {
        int start = 0, end = input.length() - 1;
        while (start < end)
            if (input.charAt(start++) != input.charAt(end--))
                return false;
        return true;
    }
}

class PalindromeService {
    public boolean checkPalindrome(String input) {
        int start = 0, end = input.length() - 1;
        while (start < end)
            if (input.charAt(start++) != input.charAt(end--))
                return false;
        return true;
    }
}

interface PalindromeStrategy {
    boolean check(String input);
}

class StackStrategy implements PalindromeStrategy {
    public boolean check(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) stack.push(c);
        for (char c : input.toCharArray())
            if (c != stack.pop()) return false;
        return true;
    }
}

class DequeStrategy implements PalindromeStrategy {
    public boolean check(String input) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : input.toCharArray()) deque.addLast(c);
        while (deque.size() > 1)
            if (deque.removeFirst() != deque.removeLast())
                return false;
        return true;
    }
}