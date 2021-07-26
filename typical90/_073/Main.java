package typical90._073;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    int n;
    String[] c;
    List<List<Integer>> g;
    long[][] dp;

    public void exec() {
        n = stdin.nextInt();
        c = new String[n];
        for (int i = 0; i < n; i++) c[i] = stdin.nextString();
        g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        for (int i = 0; i < n-1; i++) {
            int a = stdin.nextInt()-1;
            int b = stdin.nextInt()-1;
            g.get(a).add(b);
            g.get(b).add(a);
        }

        dp = new long[n][3];
        dfs(0, -1);
        stdout.println(dp[0][2]);
    }

    void dfs(int pos, int pre) {
        long mod = 1000000007;
        long v1 = 1;
        long v2 = 1;

        for (int i : g.get(pos)) {
            if (i==pre) continue;
            dfs(i, pos);

            if (c[pos].equals("a")) {
                v1 *= (dp[i][0] + dp[i][2]);
                v2 *= (dp[i][0] + dp[i][1] + 2 * dp[i][2]);
            } else {
                v1 *= (dp[i][1] + dp[i][2]);
                v2 *= (dp[i][0] + dp[i][1] + 2 * dp[i][2]);
            }
            v1 %= mod;
            v2 %= mod;
        }

        if (c[pos].equals("a")) {
            dp[pos][0] = v1;
            dp[pos][2] = (v2 - v1 + mod) % mod;
        } else {
            dp[pos][1] = v1;
            dp[pos][2] = (v2 - v1 + mod) % mod;
        }
    }

    private static final Stdin stdin = new Stdin(System.in);
    private static final Stdout stdout = new Stdout(System.out);

    public static void main(String[] args) {
        try {
            new Main().exec();
        } finally {
            stdout.flush();
        }
    }

    public static class Stdin {
        private Deque<String> queue;
        private BufferedReader in;
        private Pattern space;

        public Stdin(InputStream in) {
            this.queue = new ArrayDeque<>();
            this.in = new BufferedReader(new InputStreamReader(in));
            this.space = Pattern.compile(" ");
        }

        public String nextString() {
            if (queue.isEmpty()) {
                try {
                    String line = in.readLine();
                    if (line == null) {
                        throw new EOFException();
                    }
                    space.splitAsStream(line).forEach(this.queue::addLast);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
            return queue.removeFirst();
        }

        public int nextInt() {
            return Integer.parseInt(nextString());
        }

        public double nextDouble() {
            return Double.parseDouble(nextString());
        }

        public long nextLong() {
            return Long.parseLong(nextString());
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(nextString());
        }
    }

    public static class Stdout {
        private PrintWriter stdout;

        public Stdout(PrintStream stdout) {
            this.stdout =  new PrintWriter(stdout, false);
        }

        public void println(Object ... objs) {
            for (int i = 0, len = objs.length; i < len; i++) {
                stdout.print(objs[i]);
                if (i != len-1) stdout.print(' ');
            }
            stdout.println();
        }

        public void flush() {
            stdout.flush();
        }
    }
}