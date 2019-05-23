package b;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.next();
        
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (ch == 'B') {
                if (!stack.isEmpty()) {
                    stack.removeFirst();
                }
            } else {
                stack.addFirst(ch);
            }
        }
        
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.removeLast());
        }
        
        System.out.println(ans);
    }
}
