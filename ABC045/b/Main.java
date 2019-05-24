package b;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        
        Map<Character, String> s = new HashMap<>();
        s.put('a', stdin.nextLine());
        s.put('b', stdin.nextLine());
        s.put('c', stdin.nextLine());
        
        char turn = 'a';
        while (!s.get(turn).isEmpty()) {
            String t = s.get(turn);
            s.put(turn, t.substring(1));
            turn = t.charAt(0);
        }
        
        System.out.println(Character.toUpperCase(turn));
    }
}
