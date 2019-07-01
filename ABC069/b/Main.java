package b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String s = stdin.next();
        char x = s.charAt(0);
        int y = s.length() - 2;
        char z = s.charAt(s.length() - 1);
        System.out.printf("%c%d%c%n", x, y, z);
    }
}
