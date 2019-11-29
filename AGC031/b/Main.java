package b;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(stdin.readLine());
        }

        Deque<Integer> b = new ArrayDeque<>();
        b.addLast(c[0]);
        for (int i = 1; i < n; i++) {
            if (b.getLast() != c[i]) {
                b.addLast(c[i]);
            }
        }


        long mod = 1000000000 + 7;
        Map<Integer, Long> sm = new HashMap<>();
        long[] dp = new long[b.size() + 1];
        dp[0] = 1;
        for (int i = 0; i < dp.length - 1; i++) {
            int v = b.removeFirst();
            dp[i + 1] += dp[i] + sm.getOrDefault(v, 0L);
            dp[i + 1] %= mod;
            sm.put(v, sm.getOrDefault(v, 0L) + dp[i]);
        }

        stdout.println(dp[dp.length - 1]);
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
