package past202004.k;
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
        int n = stdin.nextInt();
        String s = stdin.nextString();
        long[] c = new long[n];
        long[] d = new long[n];
        for (int i = 0; i < n; i++) c[i] = stdin.nextLong();
        for (int i = 0; i < n; i++) d[i] = stdin.nextLong();
        
        long inf = Long.MAX_VALUE/2-1;
        long[][] dp = new long[n+1][n+1];
        for (long[] row : dp) Arrays.fill(row, inf);
        dp[0][0] = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == '(') {
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]); // 何もしない
                    if (j-1>=0) dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]+c[i]); // ')'に変更する 
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+d[i]); // 消す
                } else {
                    if (j-1>=0) dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]); // 何もしない
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+c[i]); // '('に変更する
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+d[i]); // 消す
                }
            }
        }

        stdout.println(dp[n][0]);
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