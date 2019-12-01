package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        long n = Long.parseLong(stdin.readLine());
        long a = ((100 * n) % 108 == 0) ? 100 * n / 108 : 100 * n / 108 + 1;
        long b = ((100 * (n + 1)) % 108 == 0) ? 100 * (n + 1) / 108 - 1 : 100 * (n + 1) / 108;

        if (a <= b) {
            stdout.println(a);
        } else {
            stdout.println(":(");
        }
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
