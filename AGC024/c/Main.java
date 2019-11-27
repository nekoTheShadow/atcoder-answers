package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(stdin.readLine());
        }

        if (a[0] != 0) {
            stdout.println(-1);
            return ;
        }

        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] + 1 == a[i + 1]) {
                ans += 1;
            } else if (a[i] >= a[i + 1]) {
                ans += a[i + 1];
            } else {
                stdout.println(-1);
                return ;
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
