package c;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long n = stdin.nextLong();
        long p = stdin.nextLong();

        if (n == 1) {
            System.out.println(p);
            System.exit(0);
        }

        long q = (long) Math.sqrt(p);
        long ans = 1;
        for (long i = 2; i <= q; i++) {
            long v = 0;
            while (p % i == 0) {
                v++;
                p /= i;
            }
            if (n <= v) {
                ans *= (long)Math.pow((double)i, (double)(v / n));
            }
        }
        System.out.println(ans);
    }
}
