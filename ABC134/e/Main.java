package e;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeMap;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            Integer a = Integer.valueOf(stdin.readLine());
            Integer b = d.lowerKey(a);
            if (b != null) {
                if (d.get(b) == 1) {
                    d.remove(b);
                } else {
                    d.put(b, d.get(b) - 1);
                }
            }
            d.put(a, d.getOrDefault(a, 0) + 1);
        }

        int ans = d.values().stream().mapToInt(Integer::intValue).sum();
        stdout.println(ans);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
