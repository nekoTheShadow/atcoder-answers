package b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long n = stdin.nextLong();
        long x = stdin.nextLong();
        long ans = 3 * (n - gcd(x, n));
        System.out.println(ans);
    }

    public static long gcd(long a, long b) {
        if (a == 0) {
            return b;
        } else {
            return gcd(b % a, a);
        }
    }
}
