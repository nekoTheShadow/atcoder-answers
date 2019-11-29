package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        String[] first = stdin.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);
        int[][] b = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] line = stdin.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                b[i][j] = line[j] - '0';
            }
        }

        int[][] a = new int[n][m];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                while (b[i + 1][j] > 0 && b[i - 1][j] > 0 && b[i][j + 1] > 0 && b[i][j - 1] > 0) {
                    a[i][j] += 1;
                    b[i + 1][j] -= 1;
                    b[i - 1][j] -= 1;
                    b[i][j + 1] -= 1;
                    b[i][j - 1] -= 1;
                }
            }
        }

        for (int[] row : a) {
            String line = Arrays.stream(row).mapToObj(Objects::toString).collect(Collectors.joining());
            stdout.println(line);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
