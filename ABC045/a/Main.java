package a;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        int h = stdin.nextInt();
        System.out.println((a + b) * h / 2);
    }
}
