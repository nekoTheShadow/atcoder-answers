package a;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.nextLine();
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            char d = (c == 'O') ? 'A' :
                     (c == 'A') ? 'O' : c;
            t.append(d);
        }
        System.out.println(t.toString());
    }
}
