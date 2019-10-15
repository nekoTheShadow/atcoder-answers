package b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Main {
    public void solve(BufferedReader stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = Integer.parseInt(stdin.readLine());
        int[] x = new int[n];
        int[] y = new int[n];
        Pattern space = Pattern.compile(" ");
        for (int i = 0; i < n; i++) {
            int[] line = space.splitAsStream(stdin.readLine()).mapToInt(Integer::parseInt).toArray();
            x[i] = line[0];
            y[i] = line[1];
        }

        Map<Tuple, Integer> counter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                int p = x[i] - x[j];
                int q = y[i] - y[j];
                Tuple key = new Tuple(p, q);
                counter.put(key, counter.getOrDefault(key, 0) + 1);
            }
        }

        int max = counter.values().stream().max(Comparator.naturalOrder()).orElse(0);
        System.out.println(n - max);
    }

    private static class Tuple {
        private int x;
        private int y;
        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Tuple)) {
                return false;
            }

            Tuple that = (Tuple)o;
            return this.x == that.x && this.y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return String.format("[x=%d, y=%d]", x, y);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdout = new PrintWriter(System.out, false);
        new Main().solve(stdin, stdout);
        stdout.flush();
    }
}
