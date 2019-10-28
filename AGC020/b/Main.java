package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Main {
    public void solve(final BufferedReader stdin, final PrintWriter stdout) throws NumberFormatException, IOException {
        int k = Integer.parseInt(stdin.readLine());
        long[] a = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToLong(Long::parseLong).toArray();

        long min = 2;
        long max = 2;
        for (int i = k - 1; i >= 0; i--) {
            min = ceilDiv(min, a[i]) * a[i];
            max = max / a[i] * a[i] + a[i] - 1;
        }

        if (min <= max) {
            stdout.printf("%d %d%n", min, max);
        } else {
            stdout.println(-1);
        }

    }

    private long ceilDiv(long x, long y) {
        if (x % y == 0) {
            return x / y;
        } else {
            return x / y + 1;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
