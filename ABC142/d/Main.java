package d;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        long a = stdin.nextLong();
        long b = stdin.nextLong();

        long x = Math.min(a, b);
        long y = Math.max(a, b);
        Set<Long> cms = new HashSet<>();
        for (long i = 1; i * i <= y; i++) {
            if (y % i != 0) continue;

            long j = y / i;
            if (x % i == 0) cms.add(i);
            if (x % j == 0) cms.add(j);
        }

        long ans = 1;
        for (long cm : cms) {
            if (isPrime(cm)) ans++;
        }

        System.out.println(ans);
    }

    private static boolean isPrime(long x) {
        if (x < 2) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;
        for (long i = 3; i * i <= x; i += 2) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
