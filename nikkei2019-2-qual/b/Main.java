package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        int[] d = toIntArray(stdin.readLine());

        long[] c = new long[Arrays.stream(d).max().getAsInt() + 1];
        for (int v : d) {
            c[v]++;
        }

        if (d[0] != 0 || c[0] != 1) {
            stdout.println(0);
            return ;
        }

        long ans = 1;
        for (int k = 0; k < c.length - 1; k++) {
            ans *= modPow(c[k], c[k + 1]);
            ans %= mod;
        }
        stdout.println(ans);
    }

    private long mod = 998244353;

    private long modPow(long x, long y) {
        long z = 1;
        while (y > 0) {
            if (y % 2 == 0) {
                x = (x * x) % mod;
                y /= 2;
            } else {
                z = (z * x) % mod;
                y--;
            }
        }
        return z;
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