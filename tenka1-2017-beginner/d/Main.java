package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        Pattern space = Pattern.compile(" ");
        int[] first = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int n = first[0];
        long k = first[1];
        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            long[] line = space.splitAsStream(stdin.readLine()).mapToLong(Long::parseLong).toArray();
            a[i] = line[0];
            b[i] = line[1];
        }

        long ans = sum(a, b, n, k);
        for (long i = highest(k); i >= 0; i--) {
            if ((k & (1 << i)) != 0) {
                long t = (k ^ (1 << i)) | ((1 << i) - 1);
                ans = Math.max(ans, sum(a, b, n, t));
            }
        }
        stdout.println(ans);
    }

    public long sum(long[] a, long[] b, long n, long t) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if ((t | a[i]) == t) {
                sum += b[i];
            }
        }
        return sum;
    }

    public long highest(long x) {
        long c = 0;
        while (x > 0) {
            c++;
            x /= 2;
        }
        return c;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
