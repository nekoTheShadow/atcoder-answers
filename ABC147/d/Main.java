package d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

public class Main {
    public void solve(Stdin stdin, PrintWriter stdout) throws NumberFormatException, IOException {
        int n = stdin.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextLong();
        }

        int[] c = new int[61];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 61L; j++) {
                if ((a[i] & (1L << j)) != 0) {
                    c[j]++;
                }
            }
        }

        long ans = 0;
        long d = 1;
        for (int i = 0; i < 61; i++) {
            ans += mul(mul(c[i], n - c[i]), d);
            ans %= mod;
            d *= 2L;
        }
        stdout.println(ans);
    }

    private long mul(long x, long y) {
        return ((x % mod) * (y % mod)) % mod;
    }

    private long mod = 1000000007L;

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
