package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(final BufferedReader stdin, final PrintWriter stdout) throws NumberFormatException, IOException {
        String[] line = stdin.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int c = Integer.parseInt(line[1]);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(stdin.readLine());
        }

        int ans = Integer.MAX_VALUE;
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                if (x == y) {
                    continue;
                }

                int count = 0;
                for (int i = 0; i < n; i++) {
                    if (i % 2 == 0) {
                        if (a[i] != x) {
                            count++;
                        }
                    } else {
                        if (a[i] != y) {
                            count++;
                        }
                    }
                }
                ans = Math.min(ans, count);
            }
        }

        stdout.println(ans * c);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
