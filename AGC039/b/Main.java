package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        Map<Integer, List<Integer>> nexts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = stdin.readLine();
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == '1') {
                    nexts.computeIfAbsent(i, unused -> new ArrayList<>()).add(j);
                    nexts.computeIfAbsent(j, unused -> new ArrayList<>()).add(i);
                }
            }
        }

        // 0:未着色, -1:white, 1:black
        int[] color = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);
        color[0] = 1;
        while (!stack.isEmpty()) {
            int current = stack.pollLast();
            for (int next : nexts.get(current)) {
                if (color[current] == color[next]) {
                    stdout.println(-1);
                    return ;
                }
                if (color[next] == 0) {
                    color[next] = color[current] * -1;
                    stack.addLast(next);
                }
            }
        }

        int[][] wf = new int[n][n];
        int inf = 100000000 + 7;
        for (int[] row : wf) {
            Arrays.fill(row, inf);
        }
        for (int i = 0; i < n; i++) {
            for (int j : nexts.get(i)) {
                wf[i][j] = 1;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    wf[i][j] = Math.min(wf[i][j], wf[i][k] + wf[k][j]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (wf[i][j] != inf) {
                    max = Math.max(max, wf[i][j]);
                }
            }
        }
        stdout.println(max + 1);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
