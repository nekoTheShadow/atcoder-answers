package ABC210.D;
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
import java.util.Arrays;
import java.util.Deque;
import java.util.regex.Pattern;

public class Main {
    public void exec() {
        int h = stdin.nextInt();
        int w = stdin.nextInt();
        long c = stdin.nextLong();
        long[][] a = new long[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                a[i][j] = stdin.nextLong();
            }
        }

        long ans = Long.MAX_VALUE;
        for (int time = 0; time < 2; time++) {
            long[][] dp = new long[h][w];
            for (long[] row : dp) Arrays.fill(row, Long.MAX_VALUE/2-1);

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (i > 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                    if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
                    ans = Math.min(ans, a[i][j] + c*(i+j) + dp[i][j]);
                    dp[i][j] = Math.min(dp[i][j], a[i][j] - c*(i+j));
                }
            }

            a = rotateClockWise(a);
            h = a.length;
            w = a[0].length;
        }

        stdout.println(ans);
    }

    private int[][] rotateClockWise(int[][] a) {
        int n = a.length;
        int m = a[0].length;

        int[][] b = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = a[n-1-j][i];
            }
        }
        return b;
    }

    private long[][] rotateClockWise(long[][] a) {
        int n = a.length;
        int m = a[0].length;

        long[][] b = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = a[n-1-j][i];
            }
        }
        return b;
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