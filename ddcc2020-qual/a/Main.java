package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        String[] first = stdin.readLine().split(" ");
        int x = Integer.parseInt(first[0]);
        int y = Integer.parseInt(first[1]);

        int ans = f(x) + f(y);
        if (x == 1 && y == 1) ans += 400000;
        stdout.println(ans);
    }

    public int f(int x) {
        if (x == 1) return 300000;
        if (x == 2) return 200000;
        if (x == 3) return 100000;
        return 0;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
