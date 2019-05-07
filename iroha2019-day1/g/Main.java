package g;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int m = stdin.nextInt();
        int k = stdin.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = stdin.nextLong();
        
        long[][] dp = new long[n + 1][m + 1];
        for (long[] row : dp) Arrays.fill(row, Long.MIN_VALUE);
        for (int i = 0; i < k; i++) dp[i][0] = 0L;
        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                for (int z = 1; z <= k; z++) {
                    if (dp[x][y] != Long.MIN_VALUE && x + z <= n) {
                        dp[x + z][y + 1] = Math.max(dp[x + z][y + 1], dp[x][y] + a[x]);
                    }
                }
            }
        }
        
        long ans = (dp[n][m] == Long.MIN_VALUE) ? -1L : dp[n][m];
        System.out.println(ans);
    }
}


/*
n, m, k = map(int, input().split())
a = list(map(int, input().split()))

dp = [[-float('inf')] * (m + 1) for _ in range(n + 1)]
for i in range(k):
    dp[i][0] = 0

for x in range(n):
    for y in range(m):
        for z in range(1, k + 1):
            if x + z <= n:
                dp[x + z][y + 1] = max(dp[x + z][y + 1], dp[x][y] + a[x])

print(-1 if dp[n][m] < 0 else dp[n][m])
*/