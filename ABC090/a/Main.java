package a;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String c1 = stdin.nextLine();
        String c2 = stdin.nextLine();
        String c3 = stdin.nextLine();
        System.out.printf("%c%c%c%n", c1.charAt(0), c2.charAt(1), c3.charAt(2));
    }
}
