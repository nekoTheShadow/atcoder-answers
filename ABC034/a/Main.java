package a;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int x = stdin.nextInt();
        int y = stdin.nextInt();
        if (x < y) {
            System.out.println("Better");
        } else {
            System.out.println("Worse");
        }
    }
}
