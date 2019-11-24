package d;

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
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        Pattern space = Pattern.compile(" ");
        int n = Integer.parseInt(stdin.readLine());
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            int[] line = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
            a[i] = line[0] - 1;
            b[i] = line[1] - 1;
        }

        List< List<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nexts.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            nexts.get(a[i]).add(b[i]);
            nexts.get(b[i]).add(a[i]);
        }

        Map<Integer, Map<Integer, Integer>> colors = new HashMap<>();
        int[] used = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(0);
        while (!stack.isEmpty()) {
            int current = stack.removeFirst();
            int color = 1;
            for (int next : nexts.get(current)) {
                if (parent[current] == next) continue;

                if (used[current] == color) color++;
                used[next] = color;
                parent[next] = current;
                stack.addLast(next);

                int x = Math.min(current, next);
                int y = Math.max(current, next);
                colors.computeIfAbsent(x, unused -> new HashMap<>()).put(y, color);
                color++;
            }
        }

        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, colors.get(a[i]).get(b[i]));
        }

        stdout.println(max);
        for (int i = 0; i < n - 1; i++) {
            stdout.println(colors.get(a[i]).get(b[i]));
        }
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
