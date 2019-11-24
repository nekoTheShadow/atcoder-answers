package c;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        String[] line = stdin.readLine().split(" ");
        long a = Long.parseLong(line[0]);
        long b = Long.parseLong(line[1]);
        long x = Long.parseLong(line[2]);

        long ans = 0;
        for (long d = 1; d <= 10; d++) {
            if (x - b * d < 0) {
                continue;
            }

            long y = (x - b * d) / a;
            ans = Math.max(ans, Math.min(pow(10, d) - 1, y));
        }

        stdout.println(Math.min(ans, 1000000000));
    }

    public long pow(long x, long y) {
        long c = 1;
        for (int i = 0; i < y; i++) {
            c *= x;
        }
        return c;
    }

    ;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
