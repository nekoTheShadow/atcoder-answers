package keyence2021.C;
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
import java.util.Deque;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        int k = stdin.nextInt();
        char[][] grid = new char[h][w];
        for (int i = 0; i < k; i++) {
            int x = stdin.nextInt()-1;
            int y = stdin.nextInt()-1;
            String c = stdin.nextString();
            grid[x][y] = c.charAt(0);
        }

        long[][] dp = new long[h][w];
        dp[0][0] = 1;
        long mod = 998244353;
        long inv3 = modInv(3, mod);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == 'R') {
                    if (j+1 < w) dp[i][j+1] += dp[i][j];
                } else if (grid[i][j] == 'D') {
                    if (i+1 < h) dp[i+1][j] += dp[i][j];
                } else if (grid[i][j] == 'X') {
                    if (j+1 < w) dp[i][j+1] += dp[i][j];
                    if (i+1 < h) dp[i+1][j] += dp[i][j];
                } else {
                    if (j+1 < w) dp[i][j+1] += 2 * inv3 * dp[i][j];
                    if (i+1 < h) dp[i+1][j] += 2 * inv3 * dp[i][j];
                }
                if (i+1 < h) dp[i+1][j] %= mod;
                if (j+1 < w) dp[i][j+1] %= mod;
            }
        }

        long ans = dp[h-1][w-1] * modPow(3, h*w-k, mod) % mod;
        stdout.println(ans);
    }

    public long modInv(long x, long mod) {
        return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(mod)).longValue();
    }

    public long modPow(long x, long y, long mod) {
        return BigInteger.valueOf(x).modPow(BigInteger.valueOf(y), BigInteger.valueOf(mod)).longValue();
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