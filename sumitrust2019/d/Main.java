package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        String s = stdin.readLine();

        List<TreeSet<Integer>> t = new ArrayList<>();
        int[] mins = new int[10];
        int[] maxs = new int[10];
        Arrays.fill(mins, Integer.MAX_VALUE);
        Arrays.fill(maxs, Integer.MIN_VALUE);
        for (int i = 0; i < 10; i++) {
            t.add(new TreeSet<>());
        }

        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - '0';
            mins[x] = Math.min(mins[x], i);
            maxs[x] = Math.max(maxs[x], i);
            t.get(x).add(i);
        }

        int ans = 0;
        for (int i = 0; i < 1000; i++) {
            int a = i / 100;
            int b = (i % 100) / 10;
            int c = i % 10;

            if (mins[a] == Integer.MAX_VALUE || maxs[c] == Integer.MIN_VALUE || mins[a] > maxs[c]) {
                continue;
            }

            Integer mid = t.get(b).higher(mins[a]);
            if (mid == null) {
                continue;
            }

            if (mid < maxs[c]) {
                ans++;
            }
        }

        stdout.println(ans);
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
