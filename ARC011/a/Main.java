package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Main {
    public void solve(final BufferedReader stdin, final PrintWriter stdout) throws NumberFormatException, IOException {
        long[] line = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToLong(Long::parseLong).toArray();
        long m = line[0];
        long n = line[1];
        long N = line[2];

        long ans = N;
        long last = N;
        long mod = 0;
        while (true) {
            long all = last + mod;
            if (all < m) {
                break;
            }

            last = (all / m) * n;
            mod = all % m;
            ans += last;
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
