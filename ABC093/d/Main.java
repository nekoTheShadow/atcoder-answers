package d;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int q = stdin.nextInt();
        long[] a = new long[q];
        long[] b = new long[q];
        for (int i = 0; i < q; i++) {
            a[i] = stdin.nextLong();
            b[i] = stdin.nextLong();
        }

        for (int i = 0; i < q; i++) {
            System.out.println(f(a[i], b[i]));
        }
    }

    private static long f(long a, long b) {
        if (a == b) {
            return (a - 1) * 2;
        }

        long c = (long)Math.floor(Math.sqrt(a * b));
        if (c * c < a * b) {
            if (c * (c + 1) < a * b) {
                return c * 2 - 1;
            } else {
                return c * 2 - 2;
            }
        } else {
            return (c - 1) * 2 - 1;
        }
    }
}
