package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        Pattern space = Pattern.compile(" ");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int[] first = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int n = first[0];
        int p = first[1];
        int[] a = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();

        long[][] dp = new long[n + 1][2];
        dp[0][a[0] % 2] = 1;
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] += dp[i][0];
            dp[i + 1][1] += dp[i][1];
            if (a[i] % 2 == 0) {
                dp[i + 1][0] += dp[i][0];
                dp[i + 1][1] += dp[i][1];
            } else {
                dp[i + 1][1] += dp[i][0];
                dp[i + 1][0] += dp[i][1];
            }
        }
        System.out.println(dp[n][p]);
    }
}
