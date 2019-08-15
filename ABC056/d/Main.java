package d;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int k = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextInt();
        }

        Arrays.sort(a);

        int lo = -1;
        int hi = n;
        while (Math.abs(hi - lo) > 1) {
            int mi = (lo + hi) / 2;
            if (ok(n, k, a, mi)) {
                hi = mi;
            } else {
                lo = mi;
            }
        }
        System.out.println(hi);
    }

    private static boolean ok(int n, int k, int[] a, int x) {
        boolean[][] dp = new boolean[n + 1][k];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dp[i + 1][j] = dp[i + 1][j] || dp[i][j];
                if (i != x && j + a[i] < k) {
                    dp[i + 1][j + a[i]] = dp[i + 1][j + a[i]] || dp[i][j];
                }
            }
        }
        return IntStream.range(Math.max(0, k - a[x]), k).anyMatch(i -> dp[n][i]);
    }
}
