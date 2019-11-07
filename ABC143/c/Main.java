package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        String s = stdin.readLine();
        int ans = n;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) == s.charAt(i)) ans--;
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
