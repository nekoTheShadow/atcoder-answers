package c;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int x = Integer.parseInt(stdin.readLine());

        int a = x / 100;
        int b = x % 100;

        int c = 0;
        for (int i = 5; i >= 1; i--) {
            c += b / i;
            b %= i;
        }

        if (c <= a) {
            stdout.println(1);
        } else {
            stdout.println(0);
        }
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
