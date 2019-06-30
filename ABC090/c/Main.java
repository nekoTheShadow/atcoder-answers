package c;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long n = stdin.nextLong();
        long m = stdin.nextLong();
        
        long x = Math.min(n, m);
        long y = Math.max(n, m);
        if (x == 1) {
            if (y == 1) {
                System.out.println(1);
            } else {
                System.out.println(y - 2);
            }
        } else {
            System.out.println((x - 2) * (y - 2));
        }
    }
}
