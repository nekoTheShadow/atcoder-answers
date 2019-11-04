package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        Pattern space = Pattern.compile(" ");
        int[] line = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int n = line[0];
        int k = line[1];
        long[] a = space.splitAsStream(stdin.readLine()).mapToLong(Long::parseLong).toArray();

        long sum = Arrays.stream(a).sum();
        List<Long> factors = new ArrayList<>();
        for (long x = 1; x * x <= sum; x++) {
            if (sum % x == 0) {
                factors.add(x);
                factors.add(sum / x);
            }
        }

        long ans = 1;
        for (long d : factors) {
            long[] c = new long[n];
            for (int x = 0; x < n; x++) {
                c[x] = a[x] % d;
            }
            Arrays.sort(c);

            long[] b1 = new long[n + 1];
            long[] b2 = new long[n + 1];
            for (int i = 0; i < n; i++) {
                b1[i + 1] = b1[i] + c[i];
                b2[i + 1] = b2[i] + (d - c[i]);
            }

            for (int x = 0; x <= n; x++) {
                long p = b1[x];
                long q = b2[n] - b2[x];
                if (p == q && p <= k) {
                    ans = Math.max(ans, d);
                }
            }
        }
        stdout.println(ans);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
