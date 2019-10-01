package a;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int c = Math.abs(a - b);
        if (c % 2 == 0) {
            System.out.println(b - c / 2);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }

}
