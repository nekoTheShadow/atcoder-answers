package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        long[] line = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToLong(Long::parseLong).toArray();
        long q = line[0] * 4; // 1L
        long h = line[1] * 2; // 1L
        long s = line[2];     // 1L
        long d = line[3];     // 2L
        long n = Long.parseLong(stdin.readLine());

        long min = Math.min(q, Math.min(h, s));
        long p1 = n * min;
        long p2 = (n / 2) * d + (n % 2) * min;
        long p = Math.min(p1, p2);
        stdout.println(p);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}