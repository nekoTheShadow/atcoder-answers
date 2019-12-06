package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        int[] a = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();

        long[] b = new long[n + 1];
        b[0] = 3;
        long ans = 1;
        for (int i = 0; i < n; i++) {
            ans *= b[a[i]];
            ans %= 1000000000 + 7;
            b[a[i]]--;
            b[a[i] + 1]++;
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
