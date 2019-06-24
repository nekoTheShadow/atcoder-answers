// package c;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long a = stdin.nextLong();
        long b = stdin.nextLong();
        long c = stdin.nextLong();
        long d = stdin.nextLong();

        // e: cとdの最小公倍数
        long e = (c * d) / gcd(c, d);

        // x: cで割り切れるもの
        // y: dで割り切れるもの
        // z: cでもdでも割り切れるもの
        long x = ((b / c * c) - (div(a, c) * c)) / c + 1;
        long y = ((b / d * d) - (div(a, d) * d)) / d + 1;
        long z = ((b / e * e) - (div(a, e) * e)) / e + 1;
        System.out.println((b - a + 1) - x - y + z);
    }

    public static long gcd(long x, long y) {
        if (x < y) {
            return gcd(y, x);
        }

        return x % y == 0 ? y : gcd(y, x % y);
    }

    public static long div(long x, long y) {
        return x % y == 0 ? x / y : x / y + 1;
    }
}