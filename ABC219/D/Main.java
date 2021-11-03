package ABC219.D;
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
        int n = stdin.nextInt();
        int x = stdin.nextInt();
        int y = stdin.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextInt();
            b[i] = stdin.nextInt();
        }
        
        int[][][] dp = new int[n+1][x+1][y+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= x; j++) {
                for (int k = 0; k <= y; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        dp[0][0][0] = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= x; j++) {
                for (int k = 0; k <= y; k++) {
                    if (dp[i][j][k]==Integer.MAX_VALUE) {
                        continue;
                    }
                    
                    int p = Math.min(j+a[i], x);
                    int q = Math.min(k+b[i], y);
                    dp[i+1][j][k] = Math.min(dp[i+1][j][k], dp[i][j][k]);
                    dp[i+1][p][q] = Math.min(dp[i+1][p][q], dp[i][j][k]+1);
                }
            }
        }
        
        if (dp[n][x][y] == Integer.MAX_VALUE) {
            stdout.println(-1);
        } else {
            stdout.println(dp[n][x][y]);
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