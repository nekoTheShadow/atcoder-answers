package a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int[] line1 = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
        int[] line2 = Pattern.compile(" ").splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();

        int m1 = line1[0];
        int d1 = line1[0];
        int m2 = line2[0];
        int d2 = line2[0];

        if (m1 == m2) {
            stdout.println(0);
        } else {
            stdout.println(1);
        }

    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
