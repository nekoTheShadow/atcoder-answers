package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int[] first = toIntArray(stdin.readLine());
        int n = first[0];
        int m = first[1];
        int l = first[2];

        int inf = Integer.MAX_VALUE / 2 - 1;
        int[][] wf1 = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(wf1[i], inf);
            wf1[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int[] line = toIntArray(stdin.readLine());
            int a = line[0] - 1;
            int b = line[1] - 1;
            int c = line[2];
            wf1[a][b] = wf1[b][a] = c;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    wf1[i][j] = Math.min(wf1[i][j], wf1[i][k] + wf1[k][j]);
                }
            }
        }

        int[][] wf2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                wf2[i][j] = wf1[i][j] <= l ? 1 : inf;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    wf2[i][j] = Math.min(wf2[i][j], wf2[i][k] + wf2[k][j]);
                }
            }
        }

        int q = Integer.parseInt(stdin.readLine());
        for (int i = 0; i < q; i++) {
            int[] line = toIntArray(stdin.readLine());
            int s = line[0] - 1;
            int t = line[1] - 1;
            if (wf2[s][t] == inf) {
                stdout.println(-1);
            } else {
                stdout.println(wf2[s][t] - 1);
            }
        }
    }

    private Pattern space = Pattern.compile(" ");

    private int[] toIntArray(String line) {
        return space.splitAsStream(line).mapToInt(Integer::parseInt).toArray();
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
