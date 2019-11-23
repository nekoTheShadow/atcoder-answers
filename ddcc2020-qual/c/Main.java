package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        String[] first = stdin.readLine().split(" ");
        int h = Integer.parseInt(first[0]);
        int w = Integer.parseInt(first[1]);
        int k = Integer.parseInt(first[2]);
        char[][] s = new char[h][w];
        for (int i = 0; i < h; i++) {
            s[i] = stdin.readLine().toCharArray();
        }

        int[] c = new int[h];
        int[] d = new int[h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (s[i][j] == '#') {
                    c[i]++;
                    d[i]++;
                }
            }
        }

        int[][] cakes = new int[h][w];
        int cake = 1;
        for (int i = 0; i < h; i++) {
            if (c[i] == 0) {
                continue;
            }

            for (int j = 0; j < w; j++) {
                cakes[i][j] = cake;
                if (s[i][j] == '#' && c[i] > 1) {
                    cake++;
                    c[i]--;
                }
            }
            cake++;
        }

        for (int i = 0; i < h; i++) {
            if (d[i] != 0) {
                continue;
            }

            boolean ok = false;
            for (int x = i; x < h; x++) {
                if (d[x] == 0) {
                    continue;
                }
                for (int y = 0; y < w; y++) cakes[i][y] = cakes[x][y];
                ok = true;
                break;
            }

            if (ok) {
                continue;
            }

            for (int x = i; x >= 0; x--) {
                if (d[x] == 0) {
                    continue;
                }
                for (int y = 0; y < w; y++) cakes[i][y] = cakes[x][y];
                ok = true;
                break;
            }
        }

        for (int[] row : cakes) {
            String line = Arrays.stream(row).mapToObj(Integer::toString).collect(Collectors.joining(" "));
            System.out.println(line);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
