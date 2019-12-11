package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public void solve(Stdin stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int w = stdin.nextInt();
        int h = stdin.nextInt();
        long[] p = new long[w];
        long[] q = new long[h];
        for (int i = 0; i < w; i++) p[i] = stdin.nextLong();
        for (int i = 0; i < h; i++) q[i] = stdin.nextLong();

        List<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < w; i++) tuples.add(new Tuple(p[i], 0));
        for (int i = 0; i < h; i++) tuples.add(new Tuple(q[i], 1));
        Collections.sort(tuples, Comparator.comparingLong(t -> t.x));

        long a = w + 1;
        long b = h + 1;
        long ans = 0;
        for (Tuple tuple : tuples) {
            if (tuple.y == 0) {
                ans += tuple.x * b;
                a--;
            } else {
                ans += tuple.x * a;
                b--;
            }
        }
        stdout.println(ans);
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

        public long nextLong() throws NumberFormatException, IOException {
            return Long.parseLong(next());
        }
    }

    private static class Tuple {
        private long x;
        private int y;
        public Tuple(long x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
