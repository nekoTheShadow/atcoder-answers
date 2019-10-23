package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int[] first = toIntArray(stdin.readLine());
        int n = first[0]; // 宝箱の個数
        int m = first[1]; // 鍵の個数
        int[] a = new int[m]; // 鍵の値段
        int[] c = new int[m]; // 開けることができる宝箱をbitで表現したもの
        for (int i = 0; i < m; i++) {
            a[i] = toIntArray(stdin.readLine())[0]; // 鍵の値段
            for (int j : toIntArray(stdin.readLine())) {
                c[i] = c[i] | (1 << (j - 1));
            }
        }


        // dp[どこまで鍵を見たか][開けた宝箱(bit)] = コストの最小値
        int max = Arrays.stream(a).sum() + 1;
        int[][] dp = new int[m + 1][1 << n];
        for (int[] raw : dp) {
            Arrays.fill(raw, max);
        }
        dp[0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 1 << n; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j]);
                dp[i + 1][j | c[i]] = Math.min(dp[i + 1][j | c[i]], dp[i][j] + a[i]);
            }
        }

        int ans = dp[m][(1 << n) - 1];
        if (ans == max) {
            stdout.println(-1);
        } else {
            stdout.println(ans);
        }
    }

    private Pattern space = Pattern.compile(" ");

    private int[] toIntArray(String line) {
        return space.splitAsStream(line).mapToInt(Integer::parseInt).toArray();
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
