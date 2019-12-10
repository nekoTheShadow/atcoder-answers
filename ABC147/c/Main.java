package c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public void solve(Stdin stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = stdin.nextInt();
        List<List<Integer>> oks = new ArrayList<>();
        List<List<Integer>> ngs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            oks.add(new ArrayList<>());
            ngs.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int a = stdin.nextInt();
            for (int j = 0; j < a; j++) {
                int x = stdin.nextInt() - 1;
                int y = stdin.nextInt();
                if (y == 1) {
                    oks.get(i).add(x);
                } else {
                    ngs.get(i).add(x);
                }
            }
        }

        int ans = 0;
        for (int bit = 0; bit < (1 << n); bit++) {
            boolean good = true;
            for (int i = 0; i < n; i++) {
                if ((bit & (1 << i)) == 0) {
                    continue;
                }

                for (int ok : oks.get(i)) {
                    if ((bit & (1 << ok)) == 0) {
                        good = false;
                    }
                }
                for (int ng : ngs.get(i)) {
                    if ((bit & (1 << ng)) != 0) {
                        good = false;
                    }
                }
            }

            if (good) {
                ans = Math.max(ans, Integer.bitCount(bit));
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
}
