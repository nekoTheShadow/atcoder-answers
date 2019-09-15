package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        String t = ok(s) ? "Yes" : "No";
        System.out.println(t);
    }
    
    public static boolean ok(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (i % 2 == 0) {
                if (ch == 'L') return false;
            } else {
                if (ch == 'R') return false;
            }
        }
        return true;
    }
}
