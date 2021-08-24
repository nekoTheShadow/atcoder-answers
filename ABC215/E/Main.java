package ABC215.E;
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
        char[] s = stdin.nextString().toCharArray();
        
        long[][][] dp = new long[n+1][1<<10][11];
        dp[0][0][10] = 1;
        long mod = 998244353;
        for (int i = 0; i < n; i++) {
            for (int msk = 0; msk < (1<<10); msk++) {
                for (int lst = 0; lst < 11; lst++) {
                    if (dp[i][msk][lst] == 0) {
                        continue;
                    }
                    
                    dp[i+1][msk][lst] += dp[i][msk][lst];
                    dp[i+1][msk][lst] %= mod;
                    
                    int nxt = s[i] - 'A';
                    if ((msk & (1<<nxt)) != 0 && lst != nxt) {
                        continue;
                    }
                    dp[i+1][msk|(1<<nxt)][nxt] += dp[i][msk][lst];
                    dp[i+1][msk|(1<<nxt)][nxt] %= mod;
                }
            }
        }
        
        long ans = 0;
        for (int msk = 0; msk < 1<<10; msk++) {
            for (int lst = 0; lst < 10; lst++) {
                ans += dp[n][msk][lst];
                ans %= mod;
            }
        }
        stdout.println(ans);
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