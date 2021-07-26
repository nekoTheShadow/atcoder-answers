package typical90._075;
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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {

    public void exec() {
        long n = stdin.nextLong();
        Map<Long, Long> primes = primeDivision(n);

        long x =primes.values().stream().mapToLong(Long::valueOf).sum();
        long ans = 0;
        while (x > 1) {
            x = (x%2==0) ? x/2 : (x/2+1);
            ans++;
        }
        stdout.println(ans);
    }

    public Map<Long, Long> primeDivision(long n) {
        Map<Long, Long> d = new HashMap<>();
        for (long k = 2; k*k <= n; k++) {
            while (n % k == 0) {
                d.put(k, d.getOrDefault(k, 0L) + 1);
                n  /= k;
            }
        }

        if (n > 1) {
            d.put(n, d.getOrDefault(n, 0L) + 1);
        }

        return d;
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