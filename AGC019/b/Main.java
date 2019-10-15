package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        String a = stdin.readLine();

        long[] c = new long[26];
        for (char ch : a.toCharArray()) {
            c[ch - 'a']++;
        }

        long len = (long)a.length();
        long ans = len * (len - 1L) / 2L + 1L;
        for (long v : c) {
            ans -= v * (v - 1L) / 2L;
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