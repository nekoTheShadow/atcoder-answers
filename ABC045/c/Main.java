package c;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.next();
        
        Deque<Tuple> stack = new ArrayDeque<>();
        stack.push(new Tuple("", s));
        long ans = 0;
        while (!stack.isEmpty()) {
            Tuple t = stack.removeFirst();
            
            if (t.rest.isEmpty()) {
                ans += Arrays.stream(t.form.split("@")).mapToLong(Long::parseLong).sum();
            } else {
                if (!t.form.isEmpty()) {
                    stack.addFirst(new Tuple(t.form + "@" + t.rest.substring(0, 1), t.rest.substring(1)));
                }
                stack.addFirst(new Tuple(t.form + t.rest.substring(0, 1), t.rest.substring(1)));
            }
        }
        
        System.out.println(ans);
    }
    
    public static class Tuple {
        private String form;
        private String rest;
        
        public Tuple(String form, String rest) {
            this.form = form;
            this.rest = rest;
        }
        
    }
}
