package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        char[][] s = new char[n][n];
        for (int i = 0; i < n; i++) s[i] = stdin.readLine().toCharArray();

        int a = 0;
        int c = 0;
        for (int b = 0; b < n; b++) {
            char[][] t = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    t[(i + a) % n][(j + b) % n] = s[i][j];
                }
            }
            if (ok(n, t)) c++;
        }

        stdout.println(c * n);
    }

    private boolean ok(int n, char[][] s) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s[i][j] != s[j][i]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
