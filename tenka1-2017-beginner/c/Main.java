package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        for (long a = 1; a <= 3500; a++) {
            for (long b = a; b <= 3500; b++) {
                long x = 4*a*b - a*n - b*n;
                long y = n*a*b;
                if (x > 0 && y > 0 && y % x == 0) {
                    long c = y / x;
                    stdout.printf("%d %d %d%n", a, b, c);
                    return ;
                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
