package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        Pattern space = Pattern.compile(" ");

        int m = Integer.parseInt(stdin.readLine());
        long[] d = new long[m];
        long[] c = new long[m];
        for (int i = 0; i < m; i++) {
            long[] line = space.splitAsStream(stdin.readLine()).mapToLong(Long::parseLong).toArray();
            d[i] = line[0];
            c[i] = line[1];
        }

        long a = 0;
        long b = 0;
        for (int i = 0; i < m; i++) {
            a += c[i];
            b += d[i] * c[i];
        }

        stdout.println(a - 1 + (b - 1) / 9);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
