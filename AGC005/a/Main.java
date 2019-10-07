package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();

        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (ch == 'S') {
                stack.addLast(ch);
            } else {
                if (stack.isEmpty() || stack.peekLast() == 'T') {
                    stack.addLast(ch);
                } else {
                    stack.pollLast();
                }

            }
        }
        System.out.println(stack.size());
    }
}
