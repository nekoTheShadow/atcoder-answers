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
        int k = Integer.parseInt(stdin.readLine());

        stdout.println(x + y - Math.abs(k - y));
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
