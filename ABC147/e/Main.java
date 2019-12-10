package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

public class Main {
    public void solve(Stdin stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        int[][] a = new int[h][w];
        int[][] b = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                a[i][j] = stdin.nextInt();
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                b[i][j] = stdin.nextInt();
            }
        }

        int max = (h + w) * 80;
        boolean[][][] dp = new boolean[h][w][max + 1];
        dp[0][0][Math.abs(a[0][0] - b[0][0])] = true;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int k = 0; k <= max; k++) {
                    if (!dp[i][j][k]) {
                        continue;
                    }
                    if (i < h - 1) {
                        int l = Math.abs(a[i + 1][j] - b[i + 1][j]);
                        dp[i + 1][j][Math.abs(k - l)] = true;
                        dp[i + 1][j][Math.abs(k + l)] = true;
                    }
                    if (j < w - 1) {
                        int l = Math.abs(a[i][j + 1] - b[i][j + 1]);
                        dp[i][j + 1][Math.abs(k - l)] = true;
                        dp[i][j + 1][Math.abs(k + l)] = true;
                    }
                }
            }
        }

        for (int k = 0; k <= max; k++) {
            if (dp[h - 1][w - 1][k]) {
                stdout.println(k);
                break;
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        Stdin stdin = new Stdin();
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }

    public static class Stdin {
        private Deque<String> queue;
        private BufferedReader stdin;
        private Pattern delimiter;

        public Stdin() {
            queue = new ArrayDeque<>();
            stdin = new BufferedReader(new InputStreamReader(System.in));
            delimiter = Pattern.compile(" ");
        }

        public String next() throws IOException {
            if (queue.isEmpty()) {
                delimiter.splitAsStream(stdin.readLine()).forEach(queue::addLast);
            }
            return queue.removeFirst();
        }

        public int nextInt() throws NumberFormatException, IOException {
            return Integer.parseInt(next());
        }
    }
}
