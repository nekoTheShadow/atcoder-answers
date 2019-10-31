package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.IntStream;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        String s = stdin.readLine();
        int max = IntStream.range(1, n).map(i -> zAlgorithm(s.substring(i))).max().orElse(0);
        stdout.println(max);
    }


    private int zAlgorithm(String s) {
        int n = s.length();
        int[] a = new int[n];
        int i = 1;
        int j = 0;
        a[0] = s.length();
        while (i < n) {
            while (i + j < n && s.charAt(j) == s.charAt(i + j)) {
                ++j;
            }
            a[i] = j;
            if (j == 0) {
                ++i;
                continue;
            }

            int k = 1;
            while (i + k < n && k + a[k] < j) {
                a[i + k] = a[k];
                ++k;
            }
            i += k;
            j -= k;
        }
        return IntStream.range(1, n).map(x -> a[x]).max().orElse(0);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
