package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Main {
    public void solve(final BufferedReader stdin, final PrintWriter stdout) throws NumberFormatException, IOException {
        long[] line = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToLong(Long::parseLong).toArray();
        long n = line[0];
        long a = line[1];
        long b = line[2];

        if ((b - a) % 2 == 0) {
            stdout.println("Alice");
        } else {
            stdout.println("Borys");
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
