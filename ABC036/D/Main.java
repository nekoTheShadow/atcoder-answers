package ABC036.D;
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
    private int n;
    private List<List<Integer>> graph;
    
    private long mod = 1000000007;
    private long[] fmemo;
    private long[] gmemo;
    
    public void exec() {
        n = stdin.nextInt();
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < n-1; i++) {
            int a = stdin.nextInt() - 1;
            int b = stdin.nextInt() - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        fmemo = new long[n];
        gmemo = new long[n];
        
        stdout.println(f(0, -1));
    }
    
    private long f(int x, int prev) {
        if (fmemo[x] > 0) return fmemo[x];
        
        long ans = 1;
        for (int y : graph.get(x)) {
            if (y == prev) continue;
            ans *= g(y, x);
            ans %= mod;
        }
        
        fmemo[x] = (ans + g(x, prev)) % mod;
        return fmemo[x];
    }
    
    private long g(int x, int prev) {
        if (gmemo[x] > 0) return gmemo[x];
        
        long ans = 1;
        for (int y : graph.get(x)) {
            if (y == prev) continue;
            ans *= f(y, x);
            ans %= mod;
        }
        
        gmemo[x] = ans;
        return gmemo[x];
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