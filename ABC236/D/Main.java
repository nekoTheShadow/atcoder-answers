package ABC236.D;
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
import java.util.stream.IntStream;

public class Main {
    public void exec() {
        int n = stdin.nextInt();
        int m = 2 * n;
        long[][] a = new long[m-1][m];
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                a[i][j] = stdin.nextLong();
            }
        }
        
        Deque<long[]> stack = new ArrayDeque<>();
        for (int i = 1; i < m; i++) {
            stack.addLast(new long[] {a[0][i], (1<<0) | (1<<i)});
        }
        
        long ans = 0;
        while (!stack.isEmpty()) {
            long[] tpl = stack.removeLast();
            long tot = tpl[0];
            long bit = tpl[1];
            
            int[] d = IntStream.range(0, m).filter(i -> (bit & (1<<i)) == 0).toArray();
            if (d.length == 0) {
                ans = Math.max(ans, tot);
                continue;
            }
            
            for (int i = 1; i < d.length; i++) {
                int x = d[0];
                int y = d[i];
                stack.addLast(new long[] {tot ^ a[x][y], bit | (1<<x) | (1<<y)});
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